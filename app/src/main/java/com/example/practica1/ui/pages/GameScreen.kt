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
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.graphicsLayer


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

    val radialGradientBrush = Brush.radialGradient(
        colors = listOf(
            Color(0xFF5A768F),
            Color(0xFF2C3E50)
        )
    )

    // 2. DISEÑO DE LA PANTALLA
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(radialGradientBrush)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Indicador de Pregunta
        Text(
            text = "Pregunta ${uiState.indicePreguntaActual + 1} de ${uiState.totalPreguntas}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White

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
            modifier = Modifier.padding(bottom = 16.dp),
            color = Color.White
        )

        // Opciones de Respuesta (Botones)
        uiState.preguntaActual.opciones.forEachIndexed { index, opcion ->
            AnimatedOptionButton(
                text = opcion,
                onClick = { viewModel.verificarRespuesta(index) },
                enabled = !uiState.seleccionBloqueada && !uiState.juegoTerminado
            )
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
@Composable
fun AnimatedOptionButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.60f else 1f,
        label = "buttonScale"
    )

    Button(
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF3498DB),
            contentColor = Color.White
        )
    ) {
        Text(text)
    }
}