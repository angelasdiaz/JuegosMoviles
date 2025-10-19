package com.example.practica1

data class Pregunta(
    val texto: String,
    val opciones: List<String>,       // Una lista con las 4 opciones
    val respuestaCorrecta: Int      // El número de la opción correcta (0, 1, 2, o 3)
)