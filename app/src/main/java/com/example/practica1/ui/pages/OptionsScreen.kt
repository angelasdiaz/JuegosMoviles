package com.example.practica1.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.animateFloatAsState

// Pantalla de opciones mejorada con slider de volumen
@Composable
fun OptionsScreen(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    volume: Float = 0.5f,
    onVolumeChange: (Float) -> Unit = {}
) {
    val switchInteractionSource = remember { MutableInteractionSource() }
    val isSwitchPressed by switchInteractionSource.collectIsPressedAsState()
    val switchScale by animateFloatAsState(
        targetValue = if (isSwitchPressed) 0.9f else 1f,
        label = "switchScale"
    )

    val radialGradientBrush = Brush.radialGradient(
        colors = listOf(
            Color(0xFF2C3E50),
            Color(0xFF5A768F)
        )
    )

    // Estado local del slider si no se pasa un callback
    var sliderValue by remember { mutableStateOf(volume) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(radialGradientBrush)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Opciones",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // Botón tema oscuro
        Box(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = switchScale
                    scaleY = switchScale
                }
                .fillMaxWidth(0.7f)
                .height(70.dp)
                .background(
                    color = if (isDarkTheme) Color(0xFF1F2937) else Color(0xFFCBD5E1),
                    shape = MaterialTheme.shapes.medium
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Tema oscuro",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isDarkTheme) Color.White else Color.Black
                )
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = onThemeChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFF3498DB),
                        uncheckedThumbColor = Color(0xFFE74C3C)
                    ),
                    interactionSource = switchInteractionSource
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Volumen Slider
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(
                    color = if (isDarkTheme) Color(0xFF1F2937) else Color(0xFFCBD5E1),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Volumen: ${(sliderValue * 100).toInt()}%",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDarkTheme) Color.White else Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Slider(
                value = sliderValue,
                onValueChange = {
                    sliderValue = it
                    onVolumeChange(it)
                },
                valueRange = 0f..1f,
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF3498DB),
                    activeTrackColor = Color(0xFF3498DB),
                    inactiveTrackColor = if (isDarkTheme) Color.Gray else Color.LightGray
                )
            )
        }
    }
}