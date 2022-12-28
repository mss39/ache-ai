package com.ifalsi.acheai.viewmodel.anuncio

import com.ifalsi.acheai.models.Anuncio
import com.ifalsi.acheai.repositories.AnunciosListRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.Call

class AnuncioViewModelTest {

    private val repository = mockk<AnunciosListRepository>()
    private val getAllAnuncios = AnuncioViewModel(repository).getAllAnuncios()

    @Test
    fun get_test() = runBlocking{

        val anuncios = listOf<Anuncio>(
            Anuncio(
                "teste",
                "teste",
                "teste",
                "teste",
                "teste",
                200.0
            )
        ) as Call<List<Anuncio>>

        coEvery { repository.getAllAnuncios() } returns anuncios

        val result = getAllAnuncios

        Assert.assertEquals(result, anuncios)
    }
}