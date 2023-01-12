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

    fun saveAnuncio(token: String, anuncio: Anuncio){
        val request = anunciosListRepository.saveAnuncio(token, anuncio)
        request.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.i("mk1","foi")
                status.postValue(response.code() == 200)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                status.postValue(false)
            }

        })


    }


    fun getAllAnuncios(token : String){

        val request = anunciosListRepository.getAllAnuncios(token)

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