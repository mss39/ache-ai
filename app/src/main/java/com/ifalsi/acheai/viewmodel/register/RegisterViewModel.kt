package com.ifalsi.acheai.viewmodel.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ifalsi.acheai.models.User
import com.ifalsi.acheai.repositories.UserRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class RegisterViewModel constructor(private val repository: UserRepository) : ViewModel() {

    val status = MutableLiveData<Boolean>()

    fun register(user: User) {

        val request = repository.saveUser(user)
        request.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.code() == HttpURLConnection.HTTP_CREATED) {
                    status.postValue(true)
                } else {
                    status.postValue(false)
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                status.postValue(false)
            }
        })

    }

}