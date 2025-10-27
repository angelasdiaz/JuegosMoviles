package com.example.practica1.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.practica1.Destination

@Composable
fun AppNavigationRail(
    allScreens: List<Destination>,
    onTabSelected: (Destination) -> Unit,
    currentScreen: Destination,
    modifier: Modifier = Modifier // Recibe el modifier (alineación) desde MainActivity
) {
    Card(
        modifier = modifier
            .width(30.dp),

        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            allScreens.forEach { screen ->
                NavigationRailItem(
                    selected = (currentScreen == screen),
                    onClick = { onTabSelected(screen) },
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.route
                        )
                    },
                    alwaysShowLabel = false
                )
            }
        }
    }
}