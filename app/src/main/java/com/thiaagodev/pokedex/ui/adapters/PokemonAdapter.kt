package com.thiaagodev.pokedex.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiaagodev.pokedex.databinding.PokemonItemBinding
import com.thiaagodev.pokedex.service.model.Pokemon
import com.thiaagodev.pokedex.ui.viewholders.PokemonViewHolder


class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {
    private var pokemonList: List<Pokemon> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val item = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PokemonViewHolder(item)
    }

    override fun getItemCount(): Int {
        return pokemonList.count()
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position], position)
    }

    fun updatePokemonList(list: List<Pokemon>) {
        pokemonList = list
        notifyDataSetChanged()
    }
}