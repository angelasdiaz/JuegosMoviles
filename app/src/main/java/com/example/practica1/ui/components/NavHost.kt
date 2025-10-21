// NavHost.kt (Reemplazado)
package com.example.practica1.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavType
import androidx.navigation.navArgument

import com.example.practica1.ui.pages.MainMenuScreen
import com.example.practica1.ui.pages.OptionsScreen
import com.example.practica1.ui.pages.GameScreen
import com.example.practica1.ui.pages.EndGameScreen

import com.example.practica1.EndGame
import com.example.practica1.Game
import com.example.practica1.MainMenu
import com.example.practica1.Options


// Funcion utilizada para la navegacion entre pantallas
@Composable
fun CustomNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainMenu.route,
        modifier = modifier
    ) {
        // Botones y navegacion dentro del menu principal
        composable(route = MainMenu.route) {
            MainMenuScreen(
                onSeeGameClick ={
                    navController.navigate(Game.route) {launchSingleTop = true}
                },
                onSeeOptionsClick ={
                    navController.navigate(Options.route) {launchSingleTop = true}
                }
            )
        }

        // Botones y navegacion dentro del menu de opciones
        composable(route = Options.route) {
            OptionsScreen()
        }

        // Botones y navegacion dentro del menu del juego
        composable(route = Game.route) {
            // NOTA: GameScreen ahora necesita el NavController para navegar a EndGame
            GameScreen(navController = navController)
        }

        // Botones y navegacion dentro del menu final (¡Ahora con argumentos!)
        composable(
            route = EndGame.route,
            // 1. Definimos los argumentos que esperamos recibir
            arguments = listOf(
                navArgument("puntuacion") { type = NavType.IntType },
                navArgument("totalPreguntas") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            // 2. Extraemos los argumentos de la ruta
            val puntuacion = backStackEntry.arguments?.getInt("puntuacion") ?: 0
            val totalPreguntas = backStackEntry.arguments?.getInt("totalPreguntas") ?: 1

            // 3. Pasamos los argumentos a la pantalla
            EndGameScreen(
                navController = navController,
                puntuacion = puntuacion,
                totalPreguntas = totalPreguntas
            )
        }
    }
}