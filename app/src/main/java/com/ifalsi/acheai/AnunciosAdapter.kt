package com.ifalsi.acheai

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.zip.Inflater

class AnunciosAdapter(val context: Context, val list: ArrayList<Anuncio>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(index: Int): Any {
        return list[index]
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup?): View? {

        var layout : View? = view
        val inflater = LayoutInflater.from(context)

        if(layout == null){
            layout = inflater.inflate(R.layout.item_list_anuncios,viewGroup, false)
        }

        val anuncio = getItem(index) as Anuncio

        val titulo = layout!!.findViewById<TextView>(R.id.txt_item_titulo)
        val endereco_anuncio = layout.findViewById<TextView>(R.id.txt_item_anuncio_endereco)
        val valor = layout.findViewById<TextView>(R.id.txt_item_anuncio_valor)

        titulo.text = "${anuncio.titulo}"
        endereco_anuncio.text = anuncio.endereco
        valor.text = "R$ ${anuncio.recompensa}"

        return layout
    }
}