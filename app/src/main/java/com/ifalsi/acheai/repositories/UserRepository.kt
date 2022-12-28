package com.ifalsi.acheai.repositories

import com.ifalsi.acheai.api.RetrofitService
import com.ifalsi.acheai.models.LoginRequest
import com.ifalsi.acheai.models.User

class UserRepository constructor(private val retrofitService: RetrofitService) {

    fun saveUser(user: User) = retrofitService.saveUser(user)

    fun login(loginRequest: LoginRequest) = retrofitService.login(loginRequest)
}