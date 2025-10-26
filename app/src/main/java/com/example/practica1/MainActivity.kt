package com.example.practica1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.practica1.ui.components.CustomTabRow
import com.example.practica1.ui.components.CustomNavHost
import com.example.practica1.ui.theme.Practica1Theme
import com.example.practica1.MainMenu

// Funcion principal
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Estado que controla si está activado el tema oscuro
            var isDarkTheme by remember { mutableStateOf(false) }

            // Estado global del volumen
            var volume by remember { mutableStateOf(0.5f) }

            Practica1Theme(darkTheme = isDarkTheme) {
                App(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { isDarkTheme = it },
                    volume = volume,
                    onVolumeChange = { volume = it }
                )
            }
        }
    }
}

// Composable principal de la app
@Composable
fun App(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    volume: Float,
    onVolumeChange: (Float) -> Unit
) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = tabRowScreens.find { it.route == currentDestination?.route } ?: MainMenu

    Scaffold(
        topBar = {
            CustomTabRow(
                allScreens = tabRowScreens,
                onTabSelected = { newScreen ->
                    navController.navigate(newScreen.route) { launchSingleTop = true }
                },
                currentScreen = currentScreen
            )
        }
    ) { innerPadding ->
        CustomNavHost(
            navController = navController,
            isDarkTheme = isDarkTheme,
            onThemeChange = onThemeChange,
            volume = volume,
            onVolumeChange = onVolumeChange,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

