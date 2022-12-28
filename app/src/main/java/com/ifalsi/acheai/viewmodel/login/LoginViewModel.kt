package com.ifalsi.acheai.viewmodel.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ifalsi.acheai.models.LoginRequest
import com.ifalsi.acheai.models.LoginResponse
import com.ifalsi.acheai.repositories.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class LoginViewModel constructor(private val userRepository: UserRepository) : ViewModel(){

    val success = MutableLiveData<LoginResponse>()
    val errorMessage = MutableLiveData<String>()

    fun login(loginRequest: LoginRequest) {

        val request = userRepository.login(loginRequest)
        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    success.postValue(response.body())
                } else {
                    errorMessage.postValue("Não foi possivel entrar. Verifique seu usuário e senha.")
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                errorMessage.postValue(t.message)

            }
        })

    }

}