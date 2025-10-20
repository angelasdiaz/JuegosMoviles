package com.example.practica1.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.practica1.ui.pages.MainMenuScreen
import com.example.practica1.ui.pages.OptionsScreen
import com.example.practica1.ui.pages.GameScreen
import com.example.practica1.ui.pages.EndGameScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
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
        startDestination = MainMenu.route, //La pantalla de comienzo siempre sera el menu principal
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
            GameScreen(
                onSeeEndGameClick ={
                    navController.navigate(EndGame.route) {launchSingleTop = true}
                }
            )
        }
        // Botones y navegacion dentro del menu final
        composable(route = EndGame.route) {
            EndGameScreen()
        }
    }
}