package com.ifalsi.acheai.repositories

import com.ifalsi.acheai.api.RetrofitService
import com.ifalsi.acheai.models.Anuncio

class AnunciosListRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllAnuncios() = retrofitService.getAllAnuncios()

    fun saveAnuncio(anuncio: Anuncio) = retrofitService.newAnuncio(anuncio)
}