package com.example.practica1

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List

// Pantallas que forman el videojuego
interface Destination {
    val icon : ImageVector
    val route : String
}

object MainMenu : Destination {
    override val icon = Icons.Filled.Home
    override val route = "mainMenu"
}
object Game : Destination {
    override val icon = Icons.Filled.Star
    override val route = "game"
}
object Options : Destination {
    override val icon = Icons.Filled.Settings
    override val route = "options"
}

object EndGame : Destination {
    override val icon = Icons.Filled.Info
    override val route = "endGame/{puntuacion}/{totalPreguntas}"
}

object Ranking : Destination {
    override val icon = Icons.Filled.List
    override val route = "ranking"
}

// Pantallas disponibles en la barra superior
val tabRowScreens = listOf(MainMenu, Options, Game, EndGame, Ranking)