package com.ifalsi.acheai.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ifalsi.acheai.databinding.ActivityAnuncioDetalhesBinding
import com.ifalsi.acheai.models.Anuncio

class AnuncioDetalhes : AppCompatActivity() {

    private lateinit var binding : ActivityAnuncioDetalhesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnuncioDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val anuncio = intent.getSerializableExtra("anuncio") as Anuncio

        binding.apply {
            txtDetalhesTitulo.text = anuncio.titulo
            txtDetalhesDescricao.text = anuncio.descricao
            txtDetalhesEndereco.text = anuncio.endereco
            txtDetalhesValor.text = "R$ ${anuncio.recompensa}"
        }

    }
}