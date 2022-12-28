package com.ifalsi.acheai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ifalsi.acheai.api.RetrofitService
import com.ifalsi.acheai.databinding.ActivityLoginBinding
import com.ifalsi.acheai.databinding.ActivityMainBinding
import com.ifalsi.acheai.models.LoginRequest
import com.ifalsi.acheai.models.UserSession
import com.ifalsi.acheai.repositories.UserRepository
import com.ifalsi.acheai.util.Validator.validateEmail
import com.ifalsi.acheai.util.Validator.validatePassword
import com.ifalsi.acheai.viewmodel.login.LoginViewModel
import com.ifalsi.acheai.viewmodel.login.LoginViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var viewModel: LoginViewModel
    private val retrofitService = RetrofitService.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        viewModel =
            ViewModelProvider(this, LoginViewModelFactory(UserRepository(retrofitService))).get(
                LoginViewModel::class.java
            )

        setupUi()
    }

    private fun setupUi() = _binding.apply {
        btnLogin.setOnClickListener {
            if (!validateEmail(edtEmail.text.toString())) {

                edtEmail.error = "Preencha o email corretamente"
                edtEmail.requestFocus()
                return@setOnClickListener

            }

            if (!validatePassword(edtPassword.text.toString())) {

                edtPassword.error = "Preencha a senha de acesso"
                edtPassword.requestFocus()
                return@setOnClickListener

            }

            viewModel.login(
                LoginRequest(
                    edtEmail.text.toString(),
                    edtPassword.text.toString()
                )
            )

        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.success.observe(this, Observer {

            UserSession.setToken(it.token)
            startActivity(Intent(this@MainActivity, AnunciosHome::class.java))
            finish()

        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

    }
}