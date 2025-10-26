// GameScreen.kt (CORREGIDO)
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider // Importación necesaria
import androidx.compose.ui.platform.LocalContext // Importación necesaria
import androidx.compose.ui.text.style.TextAlign // Importación necesaria
import androidx.navigation.NavController // Importación necesaria
import androidx.compose.runtime.LaunchedEffect

// Pantalla de juego
@Composable
fun GameScreen(
    navController: NavController,
    gameViewModelFactory: ViewModelProvider.Factory? = null,
    viewModel: GameViewModel = viewModel(factory = gameViewModelFactory)
) {
    val uiState = viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.resetGame()
    }

    // LÓGICA DE NAVEGACIÓN
    if (uiState.juegoTerminado) {
        navController.navigate(EndGame.route
            .replace("{puntuacion}", uiState.puntuacion.toString())
            .replace("{totalPreguntas}", uiState.totalPreguntas.toString())
        ) {
            popUpTo(Game.route) { inclusive = true }
        }
    }

    val radialGradientBrush = Brush.radialGradient(
        colors = listOf(
            Color(0xFF5A768F),
            Color(0xFF2C3E50)
        )
    )

    val scrollState = rememberScrollState()

    val context = LocalContext.current

    // DISEÑO DE LA PANTALLA
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(radialGradientBrush)
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Cronometro
        val mins = uiState.tiempoRestante / 60
        val segs = uiState.tiempoRestante % 60
        Text (
            text = String.format("%d:%02d", mins, segs),
            fontSize = 22.sp,
            color = when {
                uiState.tiempoRestante <= 10 -> Color.Red
                uiState.tiempoRestante <= 30 -> Color.Yellow
                else -> Color.White
            },
        )
        // Indicador de Pregunta
        Text(
            text = "Pregunta ${uiState.indicePreguntaActual + 1} de ${uiState.totalPreguntas}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )

        uiState.preguntaActual.imageName?.let { imageName ->
            val imageId = context.resources.getIdentifier(imageName, "drawable", context.packageName) //

            if (imageId != 0) { // 0 = no encontrado
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "imagenAsociada",
                    modifier = Modifier
                        .width(275.dp)
                        .height(275.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Texto de la Pregunta
        Text(
            text = uiState.preguntaActual.texto,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp),
            color = Color.White,
            textAlign = TextAlign.Center // Centra el texto de la pregunta
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

@Composable
fun AnimatedOptionButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember(enabled) { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) 0.75f else 1f,
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

@Preview
@Composable
fun GameScreen_preview() {
    Text("GameScreen Preview: Requiere NavController y Factory")
}