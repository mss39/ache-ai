package com.ifalsi.acheai.models

import java.io.Serializable

data class Anuncio(
    val title : String,
    val description : String,
    val status : String,
    val address : String,
    val category : String,
    val reward : Double
) : Serializable