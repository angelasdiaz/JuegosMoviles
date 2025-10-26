package com.example.practica1

object BancoDePreguntas {

    // Base de datos de las preguntas
    fun obtenerPreguntas(): List<Pregunta> {
        return listOf(
            Pregunta(
                texto = "¿Qué canción de las siguientes fue la más escuchada en España en 2022?",
                opciones = listOf("Despechá - Rosalía", "Quevedo: Bzrp Music Sessions, Vol.52", "La Bachata - Manuel Turizo", "Tarot - Bad Bunny, Jhayco"),
                respuestaCorrecta = 1,
                dificultad = 0,
                imageId = R.drawable.mix1
            ),
            Pregunta(
                texto = "¿A qué álbum pertenece la canción de la imagen?",
                opciones = listOf("Un verano sin ti", "Vice Versa", "Donde quiero estar", "Easy Money Baby"),
                respuestaCorrecta = 2,
                dificultad = 0,
                imageId = R.drawable.playadelingles
            ),
            Pregunta(
                texto = "¿Cómo se llama este éxito viral de Sabrina Carpenter que dominó las listas de éxitos en 2024?",
                opciones = listOf("Please Please Please", "Espresso", "Feather", "Nonsense"),
                respuestaCorrecta = 1,
                dificultad = 0,
                imageId = R.drawable.mix2
            ),
            Pregunta(
                texto = "¿Cómo se llama esta colaboración de Mark Ronson y Bruno Mars?",
                opciones = listOf("24K Magic", "Locked Out of Heaven", "Treasure", "Uptown Funk"),
                respuestaCorrecta = 3,
                dificultad = 0,
                imageId = R.drawable.uptownfunk
            ),
            Pregunta(
                texto = "¿Cuál fue el álbum debut del artista emergente Rusowsky?",
                opciones = listOf("Pórtate bien!", "Daisy", "Data", "Primera musa"),
                respuestaCorrecta = 2,
                dificultad = 0,
                imageId = R.drawable.mix5
            ),
            Pregunta(
                texto = "¿A qué álbum pertenece la canción de la imagen?",
                opciones = listOf("Desde el Fin del Mundo", "MTFC", "Supernova", "Data"),
                respuestaCorrecta = 3,
                dificultad = 0,
                imageId = R.drawable.volver
            ),
            Pregunta(
                texto = "¿A qué artistas internacionales pertenece la canción que está sonando?",
                opciones = listOf("Taylor Swift y Sabrina Carpenter", "Taylor Swift y Olivia Rodrigo", "Taylor Swift y Charli XCX", "Taylor Swift y Gracie Abrams"),
                respuestaCorrecta = 0,
                dificultad = 0,
                audioResId = R.raw.thelifeofashowgirl,
                imageId = R.drawable.thelifeofashowgirl
            ),
            Pregunta(
                texto = "¿Cuál fue el primer álbum de Sabrina Carpenter?",
                opciones = listOf("Short n’ Sweet", "Can't Blame a Girl for Trying", "Emails I Cant Send", "Eyes Wide Open"),
                respuestaCorrecta = 3,
                dificultad = 0,
                imageId = R.drawable.mix3
            ),
            Pregunta(
                texto = "¿A qué grupo de rock español pertenece la canción que está sonando?",
                opciones = listOf("Pignoise", "Marea", "Extremoduro", "Kaotiko"),
                respuestaCorrecta = 2,
                dificultad = 0,
                audioResId = R.raw.jesucristogarcia,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿Cuál de los siguientes fue el disco más reproducido de 2024?",
                opciones = listOf("HIT ME HARD AND SOFT - Billie Eilish", "Short n' Sweet - Sabrina Carpenter", "THE TORTURED POETS DEPARTMENT - Taylor Swift", "eternal sunshine - Ariana Grande"),
                respuestaCorrecta = 2,
                dificultad = 0,
                imageId = R.drawable.mix4
            ),
            Pregunta(
                texto = "¿De qué banda es la canción “Numb”?",
                opciones = listOf("Blink-182", "Foo Fighters", "Linkin Park", "Paramore"),
                respuestaCorrecta = 2,
                dificultad = 0,
                imageId = R.drawable.numb
            ),
            Pregunta(
                texto = "¿Cuál de estos álbumes pertenece al grupo Green Day?",
                opciones = listOf("Hybrid Theory", "American Idiot", "Meteora", "Franz Ferdinand"),
                respuestaCorrecta = 1,
                dificultad = 0,
                imageId = R.drawable.mix6
            ),
            Pregunta(
                texto = "¿Qué canción corresponde con la base que está sonando?",
                opciones = listOf("Baby Hello - Rauw Alejandro", "Una última vez - Soge Culebra, Saiko", "Neverita - Bad Bunny", "Supernova - Saiko"),
                respuestaCorrecta = 3,
                dificultad = 1,
                audioResId = R.raw.supernova_,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿En cuál de las siguientes canciones no colabora el artista Bad Bunny?",
                opciones = listOf("No me conoce remix", "Te boté remix", "Me llueven 3.0", "47 - remix"),
                respuestaCorrecta = 3,
                dificultad = 1,
                imageId = R.drawable.badbunny
            ),
            Pregunta(
                texto = "Esta icónica melodía de sintetizador que está sonando pertenece a uno de los mayores éxitos de The Weekend. ¿De qué canción se trata?",
                opciones = listOf("Save Your Tears", "Starboy", "Blinding Lights", "Can’t Feel My Face"),
                respuestaCorrecta = 2,
                dificultad = 1,
                audioResId = R.raw.blinding_lights,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "Esta famosa introducción de piano pertenece a una de las baladas más importantes de Adele. ¿Cuál es?",
                opciones = listOf("Hello", "Someone Like You", "Rolling in the Deep", "Easy on Me"),
                respuestaCorrecta = 1,
                dificultad = 1,
                audioResId = R.raw.someonelikeyou,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿Cómo se llama la canción del dúo icónico de Ralphie Choo y Rusowsky que está sonando?",
                opciones = listOf("ALTAGAMA", "DtMF", "VOYCONTODO", "BBY ROMEO"),
                respuestaCorrecta = 3,
                dificultad = 1,
                audioResId = R.raw.todo,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿Cómo se llama este himno del trap argentino, con artistas como Duki, Khea y C.R.O?",
                opciones = listOf("Tumando el Club", "GIVENCHY", "Mbappe", "Nueva Era"),
                respuestaCorrecta = 0,
                dificultad = 1,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿Cómo se llama esta canción de Mother Mother?",
                opciones = listOf("Hayloft", "Arms Tonite", "Hayloft II", "Burning Pile"),
                respuestaCorrecta = 0,
                dificultad = 1,
                audioResId = R.raw.hailoft,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿Cómo se llama la canción que está sonando?",
                opciones = listOf("Clean", "Messy", "Dirty", "Soft"),
                respuestaCorrecta = 1,
                dificultad = 1,
                audioResId = R.raw.messy,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿Adivina qué canción es esta versión a piano?",
                opciones = listOf("Garden of Eden", "Elizabeth Taylor", "The ordinary", "The night we met"),
                respuestaCorrecta = 2,
                dificultad = 1,
                audioResId = R.raw.ordinary,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿En qué año fue el que más se escuchó la famosa canción de 'Señorita' de Abraham Mateo?",
                opciones = listOf("2013", "2012", "2009", "2014"),
                respuestaCorrecta = 0,
                dificultad = 1,
                imageId = R.drawable.senorita,
                audioResId = R.raw.senorita
            ),
            Pregunta(
                texto = "¿A qué canción pertenece la siguiente base instrumental?",
                opciones = listOf("Crawling", "Basket Case", "All the Small Things", "In the End"),
                respuestaCorrecta = 3,
                dificultad = 1,
                audioResId = R.raw.inthend,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿De qué canción es esta intro?",
                opciones = listOf("Bring Me to Life", "All the Things She Said", "Pressure", "On my Own"),
                respuestaCorrecta = 0,
                dificultad = 1,
                audioResId = R.raw.brigmetolife,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿Cuál es el nombre de la siguiente canción en reversa?",
                opciones = listOf("Aurora - Mora, De la Rose", "Droga - Mora, C Tangana", "Memorias - Mora", "512 - Mora"),
                respuestaCorrecta = 1,
                dificultad = 2,
                audioResId = R.raw.drogareversa,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "La siguiente canción en reversa es una de las más escuchadas de Feid, ¿de cuál se trata?",
                opciones = listOf("La inocente", "Luna", "Classy 101", "Si te la encuentras por ahí"),
                respuestaCorrecta = 0,
                dificultad = 2,
                audioResId = R.raw.inocentereversa,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "Esos violines agudos son inconfundibles, incluso al revés. ¿A qué clásico himno pop de Britney Spears pertenecen?",
                opciones = listOf("…Baby One More Time", "Gimme More", "Oops!... I Did It Again", "Toxic"),
                respuestaCorrecta = 3,
                dificultad = 2,
                audioResId = R.raw.toxicreversa,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "Esta canción usó un loop de percusión tropical que fue un éxito mundial masivo para Ed Sheeran. ¿Puedes identificarla al revés?",
                opciones = listOf("Perfect", "Shape of You", "Castle on the Hill", "Bad Habits"),
                respuestaCorrecta = 1,
                dificultad = 2,
                audioResId = R.raw.shapeofureversa,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿Cómo se llama este éxito de Duki y Khea, sacado en 2017, que se escucha en reversa?",
                opciones = listOf("WACHA", "AURORA", "She Don't Give a Fo", "QUEVASHACERHOY?"),
                respuestaCorrecta = 2,
                dificultad = 2,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿Cómo se llama este hit de 2025 interpretado por Bad Bunny, Omar Courtz y Dei V, reproducido en reversa?",
                opciones = listOf("VeLDÁ", "EoO", "Malbec", "Konbini Wars"),
                respuestaCorrecta = 0,
                dificultad = 2,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿Cuál es esta canción de la película de Barbie?",
                opciones = listOf("Speed Drive", "What Was I Made For?", "Dance The Night", "Barbie World"),
                respuestaCorrecta = 0,
                dificultad = 2,
                audioResId = R.raw.speeddrivereversa,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿De quién es esta canción en reversa?",
                opciones = listOf("Kaotiko", "Ska-P", "La Polla Records", "Extremoduro"),
                respuestaCorrecta = 0,
                dificultad = 2,
                audioResId = R.raw.nocomobarbienoreversa,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿Cuál es esta canción en reversa?",
                opciones = listOf("Cha cha cha", "Slow Down", "Come & Get it", "Von dutch"),
                respuestaCorrecta = 2,
                dificultad = 2,
                audioResId = R.raw.comegaetitreversa,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "Esta canción en reversa es la más conocida de un grupo ¿Qué grupo es?",
                opciones = listOf("Arctic Monkeys", "Twenty one pilots", "Panic at the disco", "The neighbourhood"),
                respuestaCorrecta = 1,
                dificultad = 2,
                audioResId = R.raw.stressedreversa,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿Cuál es esta canción en reversa?",
                opciones = listOf("Roxanne", "All Star", "Losing my Religion", "Take Me to Church"),
                respuestaCorrecta = 2,
                dificultad = 2,
                audioResId = R.raw.religionreversa,
                imageId = R.drawable.escucharmusica
            ),
            Pregunta(
                texto = "¿A qué grupo pertenece esta canción en reversa?",
                opciones = listOf("The Police", "Weezer", "Radiohead", "Nirvana"),
                respuestaCorrecta = 1,
                dificultad = 2,
                audioResId = R.raw.inthesunreversa,
                imageId = R.drawable.escucharmusica
            )

        )
    }
}