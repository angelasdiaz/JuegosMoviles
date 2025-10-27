package com.example.practica1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.practica1.ui.components.AppNavigationRail
import com.example.practica1.ui.components.CustomNavHost
import com.example.practica1.ui.theme.Practica1Theme
import androidx.lifecycle.lifecycleScope
import com.example.practica1.data.SettingsDataStore
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination


class MainActivity : ComponentActivity() {
    private lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsDataStore = SettingsDataStore(this)

        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            // Cargar valor guardado
            LaunchedEffect(Unit) {
                settingsDataStore.themeFlow.collectLatest {
                    isDarkTheme = it
                }
            }

            Practica1Theme(darkTheme = isDarkTheme) {
                App(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { newTheme ->
                        isDarkTheme = newTheme
                        // Guardar persistente
                        lifecycleScope.launch {
                            settingsDataStore.saveTheme(newTheme)
                        }
                    }
                )
            }
        }
    }
}

// Composable principal de la app
@Composable
fun App(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    val gameViewModel: GameViewModel = viewModel()
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = tabRowScreens.find { it.route == currentDestination?.route } ?: MainMenu

    // 1. Define la lista solo con las 3 pantallas
    val screensParaElRail = listOf(MainMenu, Options, Game)

    Scaffold { innerPadding ->
        // 2. Usa un Box para superponer elementos
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // 3. El contenido (NavHost) va en el fondo, ocupando todo
            CustomNavHost(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange,
                gameViewModel = gameViewModel,
                modifier = Modifier.fillMaxSize() // Ocupa todo el Box
            )

            // 4. El Rail "flota" encima
            AppNavigationRail(
                allScreens = screensParaElRail,
                onTabSelected = { newScreen ->
                    navController.navigate(newScreen.route) {
                        // Vuelve al inicio del grafo para no apilar pantallas
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true // <-- GUARDA EL ESTADO
                        }
                        // Evita crear una nueva pantalla si ya estás en ella
                        launchSingleTop = true
                        // Restaura el estado si vuelves a una pantalla
                        restoreState = true // <-- RESTAURA EL ESTADO
                    }
                },
                currentScreen = currentScreen,
                // Lo alinea abajo a la derecha con un margen
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
        }
    }
}