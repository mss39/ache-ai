package com.ifalsi.acheai.models

import java.io.Serializable

data class Anuncio(
    val titulo : String,
    val descricao : String,
    val status : String,
    val endereco : String,
    val categoria : String,
    val recompensa : Double
) : Serializable