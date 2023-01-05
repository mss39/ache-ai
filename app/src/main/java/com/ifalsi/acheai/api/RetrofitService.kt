package com.ifalsi.acheai.api

import com.ifalsi.acheai.models.Anuncio
import com.ifalsi.acheai.models.LoginRequest
import com.ifalsi.acheai.models.LoginResponse
import com.ifalsi.acheai.models.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitService {

    @GET("/anuncios")
    fun getAllAnuncios(@Header("Authorization") auth: String): Call<List<Anuncio>>

    @POST("/anuncios")
    fun newAnuncio(@Header("Authorization") auth: String, @Body anuncio: Anuncio) : Call<ResponseBody>

    @POST("/api/usuario")
    fun saveUser(@Body user: User): Call<ResponseBody>

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
    companion object{

        private val retrofitService : RetrofitService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.12:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance() : RetrofitService{
            return retrofitService
        }
    }
}