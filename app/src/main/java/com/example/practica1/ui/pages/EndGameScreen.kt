// EndGameScreen.kt (Reemplazado)
package com.example.practica1.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practica1.GameViewModel
import com.example.practica1.MainMenu
import androidx.navigation.NavController

// Pantalla de fin del juego

@Composable
fun EndGameScreen(
    navController: NavController,
    // Recibe los datos de la navegación
    puntuacion: Int,
    totalPreguntas: Int,
    gameViewModel: GameViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Juego Terminado!",
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Tu Puntuación Final es:",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "$puntuacion / $totalPreguntas",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                // 1. Reinicia el juego en el ViewModel
                gameViewModel.resetGame()
                // 2. Navega al menú principal
                navController.navigate(MainMenu.route) {
                    // Limpia el historial para que no se quede EndGame en el stack
                    popUpTo(MainMenu.route) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver a Jugar")
        }
    }
}

// Preview de la pantalla final
@Preview
@Composable
fun EndGameScreen_preview() {
    // Ejemplo de cómo se vería la pantalla
    EndGameScreen(
        navController = androidx.navigation.compose.rememberNavController(),
        puntuacion = 7,
        totalPreguntas = 10
    )
}