package com.example.practica1.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Brush


// Pantalla principal

@Composable
fun MainMenuScreen(
    onSeeOptionsClick: () -> Unit = {},
    onSeeRankingClick: () -> Unit = {},
    onSeeGameClick: () -> Unit = {}
) {
    val jugarInteractionSource = remember { MutableInteractionSource() }
    val isJugarPressed by jugarInteractionSource.collectIsPressedAsState()
    val jugarScale by animateFloatAsState(
        targetValue = if (isJugarPressed) 0.75f else 1f,
        label = "jugarScale"
    )

    val rankingInteractionSource = remember { MutableInteractionSource() }
    val isRankingPressed by rankingInteractionSource.collectIsPressedAsState()
    val rankingScale by animateFloatAsState(
        targetValue = if (isRankingPressed) 0.65f else 1f,
        label = "rankingScale"
    )

    val opcionesInteractionSource = remember { MutableInteractionSource() }
    val isOpcionesPressed by opcionesInteractionSource.collectIsPressedAsState()
    val opcionesScale by animateFloatAsState(
        targetValue = if (isOpcionesPressed) 0.65f else 1f,
        label = "opcionesScale"
    )

    val radialGradientBrush = Brush.radialGradient(
        colors = listOf(
            Color(0xFF2C3E50),
            Color(0xFF5A768F)
        )
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(radialGradientBrush)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Music",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Trivia",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        Button(
            onClick = { onSeeGameClick() },
            interactionSource = jugarInteractionSource,
            modifier = Modifier
                .graphicsLayer {
                    scaleX = jugarScale
                    scaleY = jugarScale
                }
                .fillMaxWidth(0.7f) // Ocupa el 70% del ancho de la pantalla
                .height(70.dp), // Altura más grande para el botón
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE74C3C), // Un color rojo vibrante para el botón
                contentColor = Color.White // Texto blanco en el botón
            )
        ) {
            Text(
                text = "Jugar",
                fontSize = 24.sp, // Tamaño de fuente más grande para el texto del botón
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { onSeeRankingClick() },
            interactionSource = rankingInteractionSource,
            modifier = Modifier
                .graphicsLayer {
                    scaleX = rankingScale
                    scaleY = rankingScale
                }
                .fillMaxWidth(0.4f) // Mismo tamaño que Opciones
                .height(70.dp),     // Mismo tamaño que Opciones
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2ECC71), // Un color verde
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Ranking",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { onSeeOptionsClick() },
            interactionSource = opcionesInteractionSource,
            modifier = Modifier
                .graphicsLayer {
                    scaleX = opcionesScale
                    scaleY = opcionesScale
                }
                .fillMaxWidth(0.4f) // También ocupa el 70% del ancho
                .height(70.dp), // Misma altura que el botón de Jugar
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3498DB), // Un color azul para el botón de opciones
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Opciones",
                fontSize = 24.sp, // Tamaño de fuente más grande
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Preview de la pantalla de inicio
@Preview
@Composable
fun MainMenuScreen_Preview(){
    MainMenuScreen(
        onSeeOptionsClick = {},
        onSeeRankingClick = {},
        onSeeGameClick = {}
    )
}
