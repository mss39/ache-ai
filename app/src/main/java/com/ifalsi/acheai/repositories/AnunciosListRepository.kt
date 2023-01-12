package com.ifalsi.acheai.repositories

import com.ifalsi.acheai.api.RetrofitService
import com.ifalsi.acheai.models.Anuncio

class AnunciosListRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllAnuncios(token : String) = retrofitService.getAllAnuncios(token)

    fun saveAnuncio(token: String, anuncio: Anuncio) = retrofitService.newAnuncio(token, anuncio)
}