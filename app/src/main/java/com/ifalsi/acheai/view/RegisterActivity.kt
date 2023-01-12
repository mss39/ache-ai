package com.ifalsi.acheai.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ifalsi.acheai.api.RetrofitService
import com.ifalsi.acheai.databinding.ActivityRegisterBinding
import com.ifalsi.acheai.models.User
import com.ifalsi.acheai.repositories.UserRepository
import com.ifalsi.acheai.util.Validator.validateEmail
import com.ifalsi.acheai.util.Validator.validateName
import com.ifalsi.acheai.util.Validator.validatePassword
import com.ifalsi.acheai.viewmodel.register.RegisterViewModel
import com.ifalsi.acheai.viewmodel.register.RegisterViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        viewModel =
            ViewModelProvider(this, RegisterViewModelFactory(UserRepository(retrofitService))).get(
                RegisterViewModel::class.java
            )

        setupUi()

    }

    private fun setupUi() = _binding.apply {

        btnRegister.setOnClickListener {

            if (!validateName(edtName.text.toString())) {

                edtName.error = "Preencha o nome completo"
                edtName.requestFocus()
                return@setOnClickListener

            }

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

            viewModel.register(
                User(
                    edtName.text.toString(),
                    edtEmail.text.toString(),
                    edtPassword.text.toString()
                )
            )

            //Toast.makeText(this@RegisterActivity, "Cadastro com Sucesso", Toast.LENGTH_LONG).show()

        }

    }

    override fun onStart() {
        super.onStart()

        viewModel.status.observe(this, Observer { status->
            if (status) {
                Toast.makeText(
                    this,
                    "Usuário registrado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Erro ao registrar usuário. Tente novamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }
}