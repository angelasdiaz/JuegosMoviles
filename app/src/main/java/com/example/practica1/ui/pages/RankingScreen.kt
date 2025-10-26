package com.example.practica1.ui.pages

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.practica1.GameViewModel
import com.example.practica1.ScoreEntry
// --- Import Añadido ---
import com.example.practica1.ui.theme.Practica1Theme

@Composable
fun RankingScreen(
    navController: NavController,
    // --- CAMBIO: Recibe solo la lista, no el ViewModel ---
    rankingList: List<ScoreEntry>
) {
    // Ya no necesitamos el ViewModel aquí
    // val rankingList = gameViewModel.uiState.rankingList

    val radialGradientBrush = Brush.radialGradient(
        colors = listOf(
            Color(0xFF5A768F),
            Color(0xFF2C3E50)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(radialGradientBrush)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ranking",
            style = MaterialTheme.typography.displaySmall,
            fontFamily = FontFamily.Cursive,
            color = Color.White,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Comprueba si la lista está vacía
        if (rankingList.isEmpty()) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Aún no hay puntuaciones.\n¡Juega una partida para ser el primero!",
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            // Lista de puntuaciones
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Ocupa el espacio disponible
            ) {
                itemsIndexed(rankingList) { index, score ->
                    ScoreEntryRow(index = index, entry = score)
                    Divider(color = Color.Gray.copy(alpha = 0.5f))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para volver al menú
        Button(
            onClick = {
                navController.popBackStack() // Simplemente vuelve a la pantalla anterior (MainMenu)
            },
            modifier = Modifier.fillMaxWidth(0.7f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3498DB),
                contentColor = Color.White
            )
        ) {
            Text("Volver", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
    }
}

// Composable para cada fila del ranking (sin cambios)
@Composable
private fun ScoreEntryRow(index: Int, entry: ScoreEntry) {
    // ... (código sin cambios) ...
    val color = when (index) {
        0 -> Color(0xFFFFD700) // Oro
        1 -> Color(0xFFC0C0C0) // Plata
        2 -> Color(0xFFCD7F32) // Bronce
        else -> Color.White
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${index + 1}. ${entry.nombre}",
            color = color,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "${entry.puntuacion} pts",
            color = color,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// --- FUNCIÓN DE PREVIEW CORREGIDA ---
@Preview
@Composable
fun RankingScreen_Preview() {
    // 1. Creamos una lista falsa directamente.
    val fakeList = listOf(
        ScoreEntry("Jugador 1", 10),
        ScoreEntry("Jugador 2", 8),
        ScoreEntry("Jugador 3", 5),
        ScoreEntry("Jugador 4", 2)
    )

    // 2. Envolvemos el Preview en nuestro tema para que el fondo se vea
    Practica1Theme(darkTheme = true) {
        RankingScreen(
            navController = rememberNavController(),
            rankingList = fakeList // <-- Le pasamos la lista falsa
        )
    }
}

