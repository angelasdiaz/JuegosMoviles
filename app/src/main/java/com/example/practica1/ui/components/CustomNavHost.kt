package com.example.practica1.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.practica1.ui.pages.MainMenuScreen
import com.example.practica1.ui.pages.OptionsScreen
//import com.example.practica1.ui.pages.GameScreen
//import com.example.practica1.ui.pages.EndGameScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.practica1.Game
import com.example.practica1.MainMenu
import com.example.practica1.Options


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
        composable(route = MainMenu.route) {
            MainMenuScreen(
                onSeeOptionsClick ={
                    navController.navigate(Options.route) {launchSingleTop = true}
                }
            )
        }
        composable(route = Options.route) {
            OptionsScreen()
        }
    }
}