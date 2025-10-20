package com.example.practica1.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun MainMenuScreen(
    onSeeOptionsClick: () -> Unit = {},
) {
    Column (
        modifier = Modifier.padding(16.dp)
    ){
        Button(onClick = { onSeeOptionsClick() }) {
            Text("Opciones")
        }
    }
}

@Preview
@Composable
fun MainMenuScreen_Preview(){
    MainMenuScreen()
}
