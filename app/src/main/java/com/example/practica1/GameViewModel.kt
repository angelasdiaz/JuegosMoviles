package com.example.practica1

import android.app.Application
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica1.data.SettingsDataStore
import com.google.gson.Gson // Importacion de Gson
import com.google.gson.reflect.TypeToken // Importacion de Gson
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException

//para el .json
private data class JsonPregunta(
    val texto: String,
    val opciones: List<String>,
    val respuestaCorrecta: Int,
    val dificultad: Int,
    val audioResName: String? = null,
    val imageName: String? = null
)

data class ScoreEntry(
    val nombre: String,
    val puntuacion: Int
)
data class GameUiState(
    val preguntaActual: Pregunta = Pregunta(
        texto = "",
        opciones = listOf("", "", "", ""),
        respuestaCorrecta = 0,
        dificultad = 0,
        audioResName = null,
        imageName = null
    ),
    val indicePreguntaActual: Int = 0,
    val puntuacion: Int = 0,
    val juegoTerminado: Boolean = false,
    val totalPreguntas: Int = 0,
    val seleccionBloqueada: Boolean = false,
    val tiempoRestante: Int = 120,
    val volumen: Float = 0.5f,
    val rankingList: List<ScoreEntry> = emptyList()
)

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = SettingsDataStore(application)
    private var mediaPlayer: MediaPlayer? = null

    private val gson = Gson()
    private val listType = object : TypeToken<List<ScoreEntry>>() {}.type
    private val scoreListType = object : TypeToken<List<ScoreEntry>>() {}.type
    var uiState by mutableStateOf(GameUiState())
        private set

    private var gameInProgress = false
    private val todasLasPreguntas: List<Pregunta> = cargarPreguntasDesdeJson()
    private var timerJob: Job? = null
    private val tiempoTotal = 120

    init {
        // Cargar volumen guardado al iniciar
        viewModelScope.launch {
            dataStore.volumeFlow.collectLatest { savedVolume ->
                uiState = uiState.copy(volumen = savedVolume)
                mediaPlayer?.setVolume(savedVolume, savedVolume)
            }
        }

        viewModelScope.launch {
            dataStore.rankingFlow.collectLatest { rankingJson ->
                try {
                    val scores: List<ScoreEntry> = gson.fromJson(rankingJson, scoreListType)
                    uiState = uiState.copy(rankingList = scores.sortedByDescending { it.puntuacion })
                } catch (e: Exception) {
                    uiState = uiState.copy(rankingList = emptyList())
                }
            }
        }
    }

    private fun cargarPreguntasDesdeJson(): List<Pregunta> {
        val jsonString: String
        val context = getApplication<Application>().applicationContext
        try {
            jsonString = context.assets.open("preguntas.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val listJsonPreguntaType = object : TypeToken<List<JsonPregunta>>() {}.type
        val jsonPreguntas: List<JsonPregunta> = Gson().fromJson(jsonString, listJsonPreguntaType)

        return jsonPreguntas.map { jsonPregunta ->
            Pregunta(
                texto = jsonPregunta.texto,
                opciones = jsonPregunta.opciones,
                respuestaCorrecta = jsonPregunta.respuestaCorrecta,
                dificultad = jsonPregunta.dificultad,
                audioResName = jsonPregunta.audioResName,
                imageName = jsonPregunta.imageName
            )
        }
    }

    fun resetGame() {

        if (gameInProgress && !uiState.juegoTerminado) return

        gameInProgress = true

        uiState = uiState.copy(
            indicePreguntaActual = 0,
            puntuacion = 0,
            juegoTerminado = false,
            totalPreguntas = todasLasPreguntas.size,
            tiempoRestante = tiempoTotal
        )
        cargarPreguntaActual()
        iniciarCronometro()
    }

    fun addScore(nombre: String, puntuacion: Int) {
        viewModelScope.launch {

            val currentRankingJson = dataStore.rankingFlow.first()
            val currentRanking: MutableList<ScoreEntry> = try {
                gson.fromJson(currentRankingJson, listType) ?: mutableListOf()
            } catch (e: Exception) {
                mutableListOf()
            }

            currentRanking.add(ScoreEntry(nombre = nombre, puntuacion = puntuacion))

            val sortedRanking = currentRanking.sortedByDescending { it.puntuacion }.take(10)

            val newRankingJson = gson.toJson(sortedRanking)

            dataStore.saveRanking(newRankingJson)
        }
    }
    fun setVolume(value: Float) {
        uiState = uiState.copy(volumen = value)
        mediaPlayer?.setVolume(value, value)

        // Guardar el volumen persistente
        viewModelScope.launch {
            dataStore.saveVolume(value)
        }
    }

    private fun reproducirAudio(audioResName: String?) {
        detenerAudio()

        if (audioResName != null) {
            val context = getApplication<Application>().applicationContext
            val audioResId = context.resources.getIdentifier(audioResName, "raw", context.packageName)

            if (audioResId != 0) { // 0 = no encontrado
                mediaPlayer = MediaPlayer.create(context, audioResId)
                mediaPlayer?.apply {
                    isLooping = true
                    setVolume(uiState.volumen, uiState.volumen)
                    start()
                }
            }
        }
    }

    private fun detenerAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun cargarPreguntaActual() {
        if (uiState.indicePreguntaActual < todasLasPreguntas.size) {
            val proximaPregunta = todasLasPreguntas[uiState.indicePreguntaActual]
            uiState = uiState.copy(
                preguntaActual = proximaPregunta,
                seleccionBloqueada = false
            )
            reproducirAudio(proximaPregunta.audioResName)
        }
    }

    fun verificarRespuesta(opcionSeleccionada: Int) {
        if (uiState.seleccionBloqueada) return
        uiState = uiState.copy(seleccionBloqueada = true)
        detenerAudio()

        val esCorrecta = opcionSeleccionada == uiState.preguntaActual.respuestaCorrecta
        val nuevaPuntuacion = if (esCorrecta) uiState.puntuacion + 1 else uiState.puntuacion

        siguientePregunta(nuevaPuntuacion)
    }

    private fun siguientePregunta(nuevaPuntuacion: Int) {
        val proximoIndice = uiState.indicePreguntaActual + 1
        val juegoTerminado = proximoIndice >= todasLasPreguntas.size

        uiState = uiState.copy(
            puntuacion = nuevaPuntuacion,
            indicePreguntaActual = proximoIndice,
            juegoTerminado = juegoTerminado
        )

        if (!juegoTerminado) {
            cargarPreguntaActual()
        } else {
            detenerAudio()
            timerJob?.cancel()
            gameInProgress = false
        }
    }

    private fun iniciarCronometro() {
        timerJob?.cancel()

        timerJob = viewModelScope.launch {
            while (uiState.tiempoRestante > 0 && !uiState.juegoTerminado) {
                delay(1000L)
                uiState = uiState.copy(tiempoRestante = uiState.tiempoRestante - 1)
            }

            if (uiState.tiempoRestante <= 0 && !uiState.juegoTerminado) {
                finalizarPorCronometro()
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
    }

    fun resumeTimer() {
        if (gameInProgress && !uiState.juegoTerminado) {
            iniciarCronometro()
        }
    }
    private fun finalizarPorCronometro() {
        detenerAudio()
        timerJob?.cancel()
        uiState = uiState.copy(
            juegoTerminado = true,
            seleccionBloqueada = true
        )
        gameInProgress = false
    }

    override fun onCleared() {
        super.onCleared()
        detenerAudio()
        timerJob?.cancel()
    }
}
