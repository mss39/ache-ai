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

    @GET("list-anuncios.json?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwMDAvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE2NzExNDc4ODYsImV4cCI6MTY3MTE1MTQ4NiwibmJmIjoxNjcxMTQ3ODg2LCJqdGkiOiIxbmExOW1WQnA5MGJGdUt5Iiwic3ViIjoiMSIsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.IpuGW0isczpNaq1OKHok6tJsjHhRWRdMpBrq4CzrM88/")
    fun getAllAnuncios() : Call<List<Anuncio>>

    @POST("announces?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwMDAvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE2NzExNDc4ODYsImV4cCI6MTY3MTE1MTQ4NiwibmJmIjoxNjcxMTQ3ODg2LCJqdGkiOiIxbmExOW1WQnA5MGJGdUt5Iiwic3ViIjoiMSIsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.IpuGW0isczpNaq1OKHok6tJsjHhRWRdMpBrq4CzrM88/")
    fun newAnuncio(@Body anuncio: Anuncio) : Call<ResponseBody>
    companion object{

        private val retrofitService : RetrofitService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.108.37/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance() : RetrofitService{
            return retrofitService
        }
    }
}