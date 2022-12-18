package com.ifalsi.acheai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ifalsi.acheai.adapters.AnuncioListAdapter
import com.ifalsi.acheai.api.RetrofitService
import com.ifalsi.acheai.databinding.ActivityAnunciosHomeBinding
import com.ifalsi.acheai.repositories.AnunciosListRepository
import com.ifalsi.acheai.viewmodel.anuncio.AnuncioViewModel
import com.ifalsi.acheai.viewmodel.anuncio.AnuncioViewModelFactory

class AnunciosHome : AppCompatActivity() {

    private lateinit var binding: ActivityAnunciosHomeBinding
    lateinit var viewModel : AnuncioViewModel

    private val retrofitService = RetrofitService.getInstance()

    private val adapter = AnuncioListAdapter{

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anuncios_home)
        binding = ActivityAnunciosHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, AnuncioViewModelFactory(AnunciosListRepository(retrofitService))).get(
            AnuncioViewModel::class.java
        )

        binding.recyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        viewModel.listAnuncio.observe(this, Observer { anuncios->
            Log.i("MK1","OnStart")
            adapter.setAnuncioList(anuncios)
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.i("MK1",it)
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllAnuncios()
    }
}

