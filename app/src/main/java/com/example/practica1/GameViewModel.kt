package com.example.practica1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.media.MediaPlayer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Estado UI del juego
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
    val volumen: Float = 0.5f // 🔊 Nuevo campo
)

// ViewModel
class GameViewModel(application: Application) : AndroidViewModel(application) {

    private var mediaPlayer: MediaPlayer? = null

    var uiState by mutableStateOf(GameUiState())
        private set

    private val todasLasPreguntas: List<Pregunta> = BancoDePreguntas.obtenerPreguntas()

    private var timerJob: Job? = null
    private val tiempoTotal = 120

    init {
        resetGame()
    }

    fun resetGame() {
        uiState = GameUiState(totalPreguntas = todasLasPreguntas.size)
        cargarPreguntaActual()
        iniciarCronometro()
    }

    // Cambia el volumen y actualiza el MediaPlayer activo
    fun setVolume(value: Float) {
        uiState = uiState.copy(volumen = value)
        mediaPlayer?.setVolume(value, value)
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
