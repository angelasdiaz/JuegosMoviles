package com.example.practica1.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Pantalla de juego

@Composable
fun GameScreen(
    onSeeEndGameClick: () -> Unit = {},
    ) {
    Column (
        modifier = Modifier.padding(16.dp)
    ){
        Button(onClick = { onSeeEndGameClick() }) {
            Text("Acabar nivel")
        }
    }
}


// Preview de la pantalla de juego
@Preview
@Composable
fun GameScreen_preview() {
    GameScreen()
}