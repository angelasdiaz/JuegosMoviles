package com.example.practica1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.practica1.ui.components.CustomTabRow
import com.example.practica1.ui.components.CustomNavHost
import com.example.practica1.ui.theme.Practica1Theme


// Funcion principal
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    Practica1Theme {

        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen = tabRowScreens.find { it.route == currentDestination?.route } ?: MainMenu

        Scaffold(
            topBar = {
                CustomTabRow(
                    allScreens = tabRowScreens, // Se muestran las pantallas almacenadas
                    onTabSelected = { newScreen ->
                        navController.navigate(newScreen.route) {launchSingleTop = true}
                    },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            CustomNavHost(
                navController = navController, // Se asigna el controlador de pantallas
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun App_Preview() {
    App()
}