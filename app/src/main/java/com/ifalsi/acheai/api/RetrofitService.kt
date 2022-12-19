package com.ifalsi.acheai.api

import com.ifalsi.acheai.models.Anuncio
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @GET("/anuncios")
    fun getAllAnuncios() : Call<List<Anuncio>>

    @POST("/anuncios")
    fun newAnuncio(@Body anuncio: Anuncio) : Call<ResponseBody>
    companion object{

        private val retrofitService : RetrofitService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.9:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance() : RetrofitService{
            return retrofitService
        }
    }
}