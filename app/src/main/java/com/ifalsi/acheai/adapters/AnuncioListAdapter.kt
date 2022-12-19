package com.ifalsi.acheai.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ifalsi.acheai.R
import com.ifalsi.acheai.databinding.ItemListAnunciosBinding
import com.ifalsi.acheai.models.Anuncio

class AnuncioListAdapter(private val onItemClicked: (Anuncio) -> Unit) :
    RecyclerView.Adapter<AnuncioViewHolder>() {

    private var anuncios = mutableListOf<Anuncio>()

    fun setAnuncioList(anuncios: List<Anuncio>){
        this.anuncios = anuncios.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnuncioViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListAnunciosBinding.inflate(inflater, parent, false)
        return AnuncioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnuncioViewHolder, position: Int) {
        val anuncio = anuncios[position]
        holder.bind(anuncio,onItemClicked)
    }

    override fun getItemCount(): Int {
        return anuncios.size
    }


}

class AnuncioViewHolder(val binding: ItemListAnunciosBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(anuncio: Anuncio, onItemClicked: (Anuncio) -> Unit) {

        binding.apply {
            txtItemAnuncioValor.text = "R$ ${anuncio.recompensa}"
            txtItemTitulo.text = anuncio.titulo
            txtItemAnuncioEndereco.text = anuncio.endereco
        }


        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)


        itemView.setOnClickListener {
            onItemClicked(anuncio)
        }

    }

}