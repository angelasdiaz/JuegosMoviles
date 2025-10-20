package com.example.practica1.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview

// Pantalla principal

@Composable
fun MainMenuScreen(
    onSeeOptionsClick: () -> Unit = {},
    onSeeGameClick: () -> Unit = {},
) {
    Column (
        modifier = Modifier.padding(16.dp)
    ){
        Button(onClick = { onSeeGameClick() }) {
            Text("Jugar")
        }
        Button(onClick = { onSeeOptionsClick() }) {
            Text("Opciones")
        }
    }
}

// Preview de la pantalla de inicio
@Preview
@Composable
fun MainMenuScreen_Preview(){
    MainMenuScreen()
}
