package com.ifalsi.acheai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ifalsi.acheai.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        binding.button.setOnClickListener {
            startActivity(Intent(this, AnunciosHome::class.java))
        }

        binding.button2.setOnClickListener {
            startActivity(Intent(this, CadastroAnuncioActivity::class.java))
        }


    }
}