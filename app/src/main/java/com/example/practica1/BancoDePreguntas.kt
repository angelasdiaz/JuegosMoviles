package com.example.practica1

object BancoDePreguntas {

    fun obtenerPreguntas(): List<Pregunta> {
        return listOf(
            // Pregunta 1
            Pregunta(
                texto = "¿Cuál es la capital de España?",
                opciones = listOf("Barcelona", "Lisboa", "Madrid", "Sevilla"),
                respuestaCorrecta = 2 // Es Madrid (posición 2 de la lista)
            ),

            // Pregunta 2
            Pregunta(
                texto = "¿De qué color es el caballo blanco de Santiago?",
                opciones = listOf("Negro", "Blanco", "Marrón", "Azul"),
                respuestaCorrecta = 1 // Es Blanco (posición 1)
            ),

            // Pregunta 3
            Pregunta(
                texto = "¿Cuánto es 2 + 2?",
                opciones = listOf("4", "3", "5", "1"),
                respuestaCorrecta = 0 // Es 4 (posición 0)
            )

            // ...Añade aquí todas las preguntas que quieras
        )
    }
}