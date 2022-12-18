package com.ifalsi.acheai.viewmodel.anuncio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ifalsi.acheai.repositories.AnunciosListRepository

class AnuncioViewModelFactory constructor(private val repository: AnunciosListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AnuncioViewModel::class.java)) {
            AnuncioViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}