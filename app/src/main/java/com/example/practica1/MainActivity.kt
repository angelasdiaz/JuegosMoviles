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
import androidx.lifecycle.lifecycleScope
import com.example.practica1.data.SettingsDataStore
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest

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
            modifier = Modifier.padding(innerPadding)
        )
    }
}
