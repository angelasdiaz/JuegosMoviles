// GameViewModel.kt
package com.example.practica1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.media.MediaPlayer
import androidx.lifecycle.viewModelScope
import kotlin.concurrent.timer
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 1. DATA CLASS para el estado que se muestra en la pantalla
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
    val seleccionBloqueada: Boolean = false, // Evita doble click
    val tiempoRestante: Int = 120 // 2 minutos
)

// 2. VIEWMODEL para la lógica del juego
// Heredamos de AndroidViewModel para tener acceso a los recursos (archivos de audio)
class GameViewModel(application: Application) : AndroidViewModel(application) {

    // MediaPlayer para reproducir el audio
    private var mediaPlayer: MediaPlayer? = null

    // El estado observable que se conecta a la pantalla
    var uiState by mutableStateOf(GameUiState())
        private set

    // Lista de todas las preguntas del banco
    private val todasLasPreguntas: List<Pregunta> = BancoDePreguntas.obtenerPreguntas()

    private var timerJob: Job? = null // Variable para el cronometro
    private val tiempoTotal = 120 // 120 segundos = 2 mins

    init {
        resetGame()
    }

    // Inicializa o reinicia el juego
    fun resetGame() {
        uiState = GameUiState(
            totalPreguntas = todasLasPreguntas.size
        )
        // Carga la primera pregunta y reproduce su audio si existe
        cargarPreguntaActual()
        iniciarCronometro()
    }

    // Lógica para reproducir el audio
    private fun reproducirAudio(audioResId: Int?) {
        // Detiene y libera cualquier reproductor anterior
        detenerAudio()

        if (audioResId != null) {
            // Crea el reproductor usando el Context de la aplicación (getApplication())
            mediaPlayer = MediaPlayer.create(getApplication(), audioResId)
            mediaPlayer?.apply {
                isLooping = true // Opcional: Para que el audio se repita hasta que se responda
                start()
            }
        }
    }

    // Detiene y libera el MediaPlayer
    private fun detenerAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    // Actualiza la pregunta actual en el estado
    private fun cargarPreguntaActual() {
        if (uiState.indicePreguntaActual < todasLasPreguntas.size) {
            val proximaPregunta = todasLasPreguntas[uiState.indicePreguntaActual]

            uiState = uiState.copy(
                preguntaActual = proximaPregunta,
                seleccionBloqueada = false
            )

            // Llama a la función de reproducción con el ID del recurso de la nueva pregunta
            reproducirAudio(proximaPregunta.audioResId)
        }
    }

    // Lógica para procesar la respuesta del usuario
    fun verificarRespuesta(opcionSeleccionada: Int) {
        if (uiState.seleccionBloqueada) return

        uiState = uiState.copy(seleccionBloqueada = true)

        detenerAudio()

        val esCorrecta = opcionSeleccionada == uiState.preguntaActual.respuestaCorrecta

        // Sumar puntuación si es correcta
        val nuevaPuntuacion = if (esCorrecta) uiState.puntuacion + 1 else uiState.puntuacion

        siguientePregunta(nuevaPuntuacion)
    }

    // Avanza a la siguiente pregunta o finaliza el juego
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
            // Asegurarse de que el audio se detenga si el juego termina
            detenerAudio()
            timerJob?.cancel()
        }
    }

    private fun iniciarCronometro() {
        timerJob?.cancel()  // Comprueba que no se haya iniciado otro cronometro

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
        uiState = uiState.copy (
            juegoTerminado = true,
            seleccionBloqueada = true
        )
    }


    // Se llama automáticamente cuando el ViewModel ya no se necesita (ej. al salir de la pantalla)
    override fun onCleared() {
        super.onCleared()
        detenerAudio()
        timerJob?.cancel()
    }
}