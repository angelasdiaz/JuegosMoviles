package com.example.practica1.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.practica1.*
import com.example.practica1.ui.pages.EndGameScreen
import com.example.practica1.ui.pages.GameScreen
import com.example.practica1.ui.pages.MainMenuScreen
import com.example.practica1.ui.pages.OptionsScreen
import com.example.practica1.ui.pages.RankingScreen

// Navegación principal entre pantallas
@Composable
fun CustomNavHost(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    gameViewModel: GameViewModel,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = MainMenu.route,
        modifier = modifier
    ) {
        // Pantalla principal
        composable(route = MainMenu.route) {
            MainMenuScreen(
                onSeeGameClick = {
                    navController.navigate(Game.route) { launchSingleTop = true }
                },
                onSeeOptionsClick = {
                    navController.navigate(Options.route) { launchSingleTop = true }
                },
                onSeeRankingClick = {
                    navController.navigate(Ranking.route) { launchSingleTop = true }
                }
            )
        }

        // Pantalla de opciones
        composable(route = Options.route) {
            OptionsScreen(
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange,
                viewModel = gameViewModel
            )
        }

        // Pantalla del juego
        composable(route = Game.route) {
            GameScreen(
                navController = navController,
                viewModel = gameViewModel // mismo ViewModel => mismo volumen
            )
        }

        // Pantalla de fin de juego (recibe argumentos)
        composable(
            route = EndGame.route,
            arguments = listOf(
                navArgument("puntuacion") { type = NavType.IntType },
                navArgument("totalPreguntas") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val puntuacion = backStackEntry.arguments?.getInt("puntuacion") ?: 0
            val totalPreguntas = backStackEntry.arguments?.getInt("totalPreguntas") ?: 1

            EndGameScreen(
                navController = navController,
                puntuacion = puntuacion,
                totalPreguntas = totalPreguntas,
                gameViewModel = gameViewModel
            )
        }

        composable(route = Ranking.route) {
            // Obtenemos el estado actual del viewModel
            val uiState = gameViewModel.uiState
            // Le pasamos *solo* la lista a la pantalla
            RankingScreen(
                navController = navController,
                rankingList = uiState.rankingList
            )
        }
    }
}
