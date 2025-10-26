package com.example.practica1

import android.app.Application
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica1.data.SettingsDataStore
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class GameUiState(
    val preguntaActual: Pregunta = Pregunta(
        texto = "",
        opciones = listOf("", "", "", ""),
        respuestaCorrecta = 0,
        dificultad = 0,
        audioResId = null
    ),
    val indicePreguntaActual: Int = 0,
    val puntuacion: Int = 0,
    val juegoTerminado: Boolean = false,
    val totalPreguntas: Int = 0,
    val seleccionBloqueada: Boolean = false,
    val tiempoRestante: Int = 120,
    val volumen: Float = 0.5f
)

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = SettingsDataStore(application)
    private var mediaPlayer: MediaPlayer? = null

    var uiState by mutableStateOf(GameUiState())
        private set

    private val todasLasPreguntas: List<Pregunta> = BancoDePreguntas.obtenerPreguntas()
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

        resetGame()
    }

    fun resetGame() {
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

    fun setVolume(value: Float) {
        uiState = uiState.copy(volumen = value)
        mediaPlayer?.setVolume(value, value)

        // Guardar el volumen persistente
        viewModelScope.launch {
            dataStore.saveVolume(value)
        }
    }

    private fun reproducirAudio(audioResId: Int?) {
        detenerAudio()

        if (audioResId != null) {
            mediaPlayer = MediaPlayer.create(getApplication(), audioResId)
            mediaPlayer?.apply {
                isLooping = true
                setVolume(uiState.volumen, uiState.volumen)
                start()
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
            reproducirAudio(proximaPregunta.audioResId)
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

    private fun finalizarPorCronometro() {
        detenerAudio()
        timerJob?.cancel()
        uiState = uiState.copy(
            juegoTerminado = true,
            seleccionBloqueada = true
        )
    }

    override fun onCleared() {
        super.onCleared()
        detenerAudio()
        timerJob?.cancel()
    }
}
