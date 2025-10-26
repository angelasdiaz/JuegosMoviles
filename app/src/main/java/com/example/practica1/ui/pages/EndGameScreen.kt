package com.example.practica1.ui.pages

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practica1.GameViewModel
import com.example.practica1.MainMenu
import androidx.navigation.NavController
import com.example.practica1.ui.theme.Practica1Theme
import androidx.compose.ui.text.TextStyle

// Pantalla de fin del juego
@Composable
fun EndGameScreen(
    navController: NavController,
    // Recibe los datos de la navegación
    puntuacion: Int,
    totalPreguntas: Int,
    gameViewModel: GameViewModel // Recibido como parámetro, ¡correcto!
) {
    val radialGradientBrush = Brush.radialGradient(
        colors = listOf(
            Color(0xFF5A768F),
            Color(0xFF2C3E50)
        )
    )

    // Variable para guardar el nombre del TextField
    var nombre by rememberSaveable { mutableStateOf("") }
    // Variable para controlar si el botón está habilitado
    val isButtonEnabled = nombre.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(radialGradientBrush)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Juego Terminado!",
            style = MaterialTheme.typography.displaySmall,
            fontFamily = FontFamily.Cursive,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Tu Puntuación Final es:",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Cursive,
            color = Color.White
        )
        Text(
            text = "$puntuacion / $totalPreguntas",
            style = MaterialTheme.typography.displayLarge,
            fontFamily = FontFamily.Cursive,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(32.dp))

        // --- CAMPO DE TEXTO PARA EL NOMBRE ---
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Introduce tu nombre", color = Color.Gray.copy(alpha = 0.9f)) }, // Color claro para el label
            singleLine = true,
            textStyle = TextStyle(color = Color.White), // <-- FORZAMOS EL TEXTO A BLANCO
            // Se ha eliminado el parámetro 'colors' que daba error
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // --- BOTÓN ACTUALIZADO ---
        Button(
            onClick = {
                // 1. Guardar la puntuación
                gameViewModel.addScore(nombre, puntuacion)
                // 2. Reinicia el juego en el ViewModel
                gameViewModel.resetGame()
                // 3. Navega al menú principal
                navController.navigate(MainMenu.route) {
                    popUpTo(MainMenu.route) { inclusive = true }
                }
            },
            enabled = isButtonEnabled, // <-- LÓGICA DE HABILITACIÓN
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF663399),
                contentColor = Color.White
            )

        ) {
            Text("Guardar y Volver al Menú", fontWeight = FontWeight.Bold)
        }
    }
}

// --- FUNCIÓN DE PREVIEW CORREGIDA ---
@Preview
@Composable
fun EndGameScreen_preview() {
    val context = LocalContext.current

    // Creamos una Factory falsa que sabe cómo crear un GameViewModel
    val fakeViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            // Asegurarse de que el contexto es el de la aplicación
            return GameViewModel(context.applicationContext as Application) as T
        }
    }

    Practica1Theme(darkTheme = true) {
        EndGameScreen(
            navController = androidx.navigation.compose.rememberNavController(),
            puntuacion = 7,
            totalPreguntas = 10,
            // Usamos la factory para crear el VM de forma segura en el Preview
            gameViewModel = viewModel(factory = fakeViewModelFactory)
        )
    }
}
// --- FIN DE LA CORRECCIÓN ---

