package com.ifalsi.acheai.viewmodel.anuncio

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ifalsi.acheai.models.Anuncio
import com.ifalsi.acheai.repositories.AnunciosListRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnuncioViewModel constructor(private val anunciosListRepository: AnunciosListRepository):
    ViewModel() {

    val listAnuncio = MutableLiveData<List<Anuncio>>()
    val status = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun saveAnuncio(anuncio: Anuncio){
        val request = anunciosListRepository.saveAnuncio(anuncio)
        request.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                status.postValue(response.code() == 200)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                status.postValue(false)
            }

        })


    }


    fun getAllAnuncios(){

        val request = anunciosListRepository.getAllAnuncios()

        request.enqueue(object : Callback<List<Anuncio>>{
            override fun onResponse(call: Call<List<Anuncio>>, response: Response<List<Anuncio>>) {
                listAnuncio.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Anuncio>>, t: Throwable) {
                errorMessage.postValue(t.message.toString())
            }

        })

    }

}