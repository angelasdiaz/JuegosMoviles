// GameScreen.kt (Reemplazado)
package com.example.practica1.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practica1.GameViewModel
import com.example.practica1.EndGame
import com.example.practica1.Game

// Pantalla de juego
@Composable
fun GameScreen(
    navController: androidx.navigation.NavController, // Necesitas el NavController para navegar
    viewModel: GameViewModel = viewModel() // Crea o encuentra el ViewModel
) {
    val uiState = viewModel.uiState

    // 1. LÓGICA DE NAVEGACIÓN: Si el juego ha terminado, vamos a la pantalla final
    if (uiState.juegoTerminado) {
        // Construye la ruta con los valores reales de puntuación y total
        navController.navigate(EndGame.route
            .replace("{puntuacion}", uiState.puntuacion.toString())
            .replace("{totalPreguntas}", uiState.totalPreguntas.toString())
        ) {
            // Esto evita que al pulsar "Atrás" se vuelva a esta pantalla de juego ya terminada
            popUpTo(Game.route) { inclusive = true }
        }
    }

    // 2. DISEÑO DE LA PANTALLA
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Indicador de Pregunta
        Text(
            text = "Pregunta ${uiState.indicePreguntaActual + 1} de ${uiState.totalPreguntas}",
            style = MaterialTheme.typography.titleMedium
        )

        // Si la pregunta actual cuenta con una foto la muestra, sino no muestra nada
        uiState.preguntaActual.imageId?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = "imagenAsociada",
                modifier = Modifier
                    .width(350.dp)
                    .height(350.dp),
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Texto de la Pregunta
        Text(
            text = uiState.preguntaActual.texto,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Opciones de Respuesta (Botones)
        uiState.preguntaActual.opciones.forEachIndexed { index, opcion ->
            Button(
                onClick = { viewModel.verificarRespuesta(index) },
                // Deshabilitar los botones si ya se pulsó o si el juego terminó
                enabled = !uiState.seleccionBloqueada && !uiState.juegoTerminado,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(opcion)
            }
        }
    }
}

// Preview de la pantalla de juego
@Preview
@Composable
fun GameScreen_preview() {
    // El preview ya no funciona directamente porque GameScreen ahora necesita el NavController y ViewModel
    // Ahora hay que envolverlo con un NavController simulado
    Text("GameScreen Preview: Requiere NavController")
}