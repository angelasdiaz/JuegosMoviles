package com.example.practica1

object BancoDePreguntas {

    fun obtenerPreguntas(): List<Pregunta> {
        return listOf(
            Pregunta(
                texto = "¿Qué canción fue la más escuchada en España en 2022?",
                opciones = listOf("Despechá - Rosalía", "Quevedo: Bzrp Music Sessions, Vol.52", "La Bachata - Manuel Turizo", "Tarot - Bad Bunny, Jhayco"),
                respuestaCorrecta = 1,
                dificultad = 0
            ),
            Pregunta(
                texto = "¿A qué álbum pertenece la siguiente canción?",
                opciones = listOf("Un verano sin ti", "Vice Versa", "Donde quiero estar", "Easy Money Baby"),
                respuestaCorrecta = 2,
                dificultad = 0
            ),
            Pregunta(
                texto = "¿Como se llama este éxito viral de Sabrina Carpenter que dominó las listas en 2024?",
                opciones = listOf("Please Please Please", "Espresso", "Feather", "Nonsense"),
                respuestaCorrecta = 1,
                dificultad = 0
            ),
            Pregunta(
                texto = "¿Como se llama esta colaboración de Mark Ronson y Bruno Mars?",
                opciones = listOf("24K Magic", "Locked Out of Heaven", "Treasure", "Uptown Funk"),
                respuestaCorrecta = 3,
                dificultad = 0
            ),
            Pregunta(
                texto = "¿A qué artistas internacionales pertenece esta canción?",
                opciones = listOf("Taylor Swift y Sabrina Carpenter", "Taylor Swift y Olivia Rodrigo", "Taylor Swift y Charli XCX", "Taylor Swift y Gracie Abrams"),
                respuestaCorrecta = 0,
                dificultad = 0,
                audioResId = R.raw.thelifeofashowgirl
            ),
            Pregunta(
                texto = "¿Cuál fue el primer álbum de Sabrina Carpenter?",
                opciones = listOf("Short n’ Sweet", "Can't Blame a Girl for Trying", "Emails I Cant Send", "Eyes Wide Open"),
                respuestaCorrecta = 3,
                dificultad = 0
            ),
            Pregunta(
                texto = "A qué grupo de rock español pertenece esta canción?",
                opciones = listOf("Pignoise", "Marea", "Extremoduro", "Kaotiko"),
                respuestaCorrecta = 2,
                dificultad = 0,
                audioResId = R.raw.jesucristogarcia
            ),
            Pregunta(
                texto = "¿Cuál fue el disco más reproducido de 2024?",
                opciones = listOf("HIT ME HARD AND SOFT - Billie Eilish", "Short n' Sweet - Sabrina Carpenter", "THE TORTURED POETS DEPARTMENT - Taylor Swift", "eternal sunshine - Ariana Grande"),
                respuestaCorrecta = 2,
                dificultad = 0
            ),
            Pregunta(
                texto = "¿Qué canción corresponde a la siguiente base?",
                opciones = listOf("Baby Hello - Rauw Alejandro", "Una última vez - Soge Culebra, Saiko", "Neverita - Bad Bunny", "Supernova - Saiko"),
                respuestaCorrecta = 3,
                dificultad = 1,
                audioResId = R.raw.supernova_
            ),
            Pregunta(
                texto = "¿En cuál de las siguientes canciones no colabora el artista Bad Bunny?",
                opciones = listOf("No me conoce remix", "Te boté remix", "Me llueven 3.0", "47 - remix"),
                respuestaCorrecta = 3,
                dificultad = 1
            ),
            Pregunta(
                texto = "Esta icónica melodía de sintetizador pertenece a uno de los mayores éxitos de The Weekend. ¿De qué canción se trata?",
                opciones = listOf("Save Your Tears", "Starboy", "Blinding Lights", "Can’t Feel My Face"),
                respuestaCorrecta = 2,
                dificultad = 1,
                audioResId = R.raw.blinding_lights
            ),
            Pregunta(
                texto = "Esta famosa introducción de piano pertenece a una de las baladas más importantes de Adele. ¿Cuál es?",
                opciones = listOf("Hello", "Someone Like You", "Rolling in the Deep", "Easy on Me"),
                respuestaCorrecta = 1,
                dificultad = 1,
                audioResId = R.raw.someonelikeyou
            ),
            Pregunta(
                texto = "¿Cómo se llama esta canción de Mother Mother?",
                opciones = listOf("Hayloft", "Arms Tonite", "Hayloft II", "Burning Pile"),
                respuestaCorrecta = 0,
                dificultad = 1,
                audioResId = R.raw.hailoft
            ),
            Pregunta(
                texto = "¿Cómo se llama esta canción?",
                opciones = listOf("Clean", "Messy", "Dirty", "Soft"),
                respuestaCorrecta = 1,
                dificultad = 1,
                audioResId = R.raw.messy
            ),
            Pregunta(
                texto = "¿Adivina qué canción es esta versión a piano?",
                opciones = listOf("Garden of Eden", "Elizabeth Taylor", "The ordinary", "The night we met"),
                respuestaCorrecta = 2,
                dificultad = 1,
                audioResId = R.raw.ordinary
            ),
            Pregunta(
                texto = "¿En qué año fue el que más se escuchó la famosa canción de 'Señorita' de Abraham Mateo?",
                opciones = listOf("2013", "2012", "2009", "2014"),
                respuestaCorrecta = 0,
                dificultad = 1
            ),
            Pregunta(
                texto = "¿Cuál es la siguiente canción en reversa?",
                opciones = listOf("Aurora - Mora, De la Rose", "Droga - Mota, C Tangana", "Memorias - Mora", "512 - Mora"),
                respuestaCorrecta = 1,
                dificultad = 2
            ),
            Pregunta(
                texto = "La siguiente canción en reversa es una de las más escuchadas de Feid, ¿de cuál se trata?",
                opciones = listOf("La inocente", "Luna", "Classy 101", "Si te la encuentras por ahí"),
                respuestaCorrecta = 0,
                dificultad = 2
            ),
            Pregunta(
                texto = "Esos violines agudos son inconfundibles, incluso al revés. ¿A qué clásico himno pop de Britney Spears pertenecen?",
                opciones = listOf("…Baby One More Time", "Gimme More", "Oops!... I Did It Again", "Toxic"),
                respuestaCorrecta = 3,
                dificultad = 2
            ),
            Pregunta(
                texto = "Esta canción usó un loop de percusión tropical que fue un éxito mundial masivo para Ed Sheeran. ¿Puedes identificarla al revés?",
                opciones = listOf("Perfect", "Shape of You", "Castle on the Hill", "Bad Habits"),
                respuestaCorrecta = 1,
                dificultad = 2
            ),
            Pregunta(
                texto = "¿Cuál es esta canción de la película de Barbie?",
                opciones = listOf("Speed Drive", "What Was I Made For?", "Dance The Night", "Barbie World"),
                respuestaCorrecta = 0,
                dificultad = 2
            ),
            Pregunta(
                texto = "¿De quién es esta canción en reversa?",
                opciones = listOf("Kaotiko", "Ska-P", "La Polla Records", "Extremoduro"),
                respuestaCorrecta = 0,
                dificultad = 2
            ),
            Pregunta(
                texto = "¿Cuál es esta canción en reversa?",
                opciones = listOf("Cha cha cha", "Slow Down", "Come & Get it", "Von dutch"),
                respuestaCorrecta = 2,
                dificultad = 2
            ),
            Pregunta(
                texto = "Esta canción en reversa es la más conocida de un grupo ¿Qué grupo es?",
                opciones = listOf("Arctic Monkeys", "Twenty one pilots", "Panic at the disco", "The neighbourhood"),
                respuestaCorrecta = 1,
                dificultad = 2
            )

        )
    }
}