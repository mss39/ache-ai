package com.ifalsi.acheai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class ListViewAnuncios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_anuncios)

        val list : ArrayList<Anuncio> = arrayListOf(
            Anuncio("Chave","Rua da Praia",100.0),
            Anuncio("Carteira","Rua da maçonaria",1000.0),
            Anuncio("Smartphone Iphone 8","Rua da Dilma",801.0),
            Anuncio("Chave de carro Chevrolet","Rua do Bolsonaro",50.0)
        )

        val listView = findViewById<ListView>(R.id.list_view_anuncios)
        listView.adapter = AnunciosAdapter(this, list)
    }
}