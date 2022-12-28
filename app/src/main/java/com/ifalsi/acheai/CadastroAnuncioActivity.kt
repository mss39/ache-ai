package com.ifalsi.acheai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ifalsi.acheai.api.RetrofitService
import com.ifalsi.acheai.databinding.ActivityCadastroAnuncioBinding
import com.ifalsi.acheai.models.Anuncio
import com.ifalsi.acheai.repositories.AnunciosListRepository
import com.ifalsi.acheai.viewmodel.anuncio.AnuncioViewModel
import com.ifalsi.acheai.viewmodel.anuncio.AnuncioViewModelFactory

class CadastroAnuncioActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCadastroAnuncioBinding
    private lateinit var viewModel: AnuncioViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCadastroAnuncioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, AnuncioViewModelFactory(AnunciosListRepository(retrofitService)))
                                    .get(AnuncioViewModel::class.java)

        setupUi()
    }

    private fun setupUi(){

        this.binding.apply {

            btnEnviarAnuncio.setOnClickListener {

                if(editTextCadastroTitulo.text.isEmpty()){
                    editTextCadastroTitulo.error = "Campo obrigatório"
                    editTextCadastroTitulo.requestFocus()
                    return@setOnClickListener
                }
                if(editTextCadastroDescricao.text.isEmpty()){
                    editTextCadastroDescricao.error = "Campo obrigatório"
                    editTextCadastroDescricao.requestFocus()
                    return@setOnClickListener
                }
                if(editTextCadastroEndereco.text.isEmpty()){
                    editTextCadastroEndereco.error = "Campo obrigatório"
                    editTextCadastroEndereco.requestFocus()
                    return@setOnClickListener
                }
                if(editTextCadastroRecompensa.text.isEmpty()){
                    editTextCadastroRecompensa.error = "Campo obrigatório"
                    editTextCadastroRecompensa.requestFocus()
                    return@setOnClickListener
                }
                if (spinnerCategoria.selectedItemPosition == 0){
                    spinnerCategoria.requestFocus()
                    return@setOnClickListener
                }

                val newAnuncio = Anuncio(
                    title = editTextCadastroTitulo.text.toString(),
                    description = editTextCadastroDescricao.text.toString(),
                    address = editTextCadastroEndereco.text.toString(),
                    reward = editTextCadastroRecompensa.text.toString().toDouble(),
                    category = spinnerCategoria.adapter.getItem(spinnerCategoria.selectedItemPosition) as String,
                    status = "Perdido"
                )

                editTextCadastroTitulo.text.clear()
                editTextCadastroDescricao.text.clear()
                editTextCadastroEndereco.text.clear()
                editTextCadastroRecompensa.text.clear()
                viewModel.saveAnuncio(newAnuncio)

            }

        }
    }
}