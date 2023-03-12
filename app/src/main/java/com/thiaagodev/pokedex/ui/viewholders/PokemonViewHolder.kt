package com.thiaagodev.pokedex.ui.viewholders

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thiaagodev.pokedex.databinding.PokemonItemBinding
import com.thiaagodev.pokedex.service.model.Pokemon
import com.thiaagodev.pokedex.ui.listeners.PokemonListener
import java.util.Locale

class PokemonViewHolder(
    private val binding: PokemonItemBinding,
    private val listener: PokemonListener?
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pokemon: Pokemon, index: Int) {
        Glide.with(binding.root.context)
            .asGif()
            .load(pokemon.getImage(index + 1))
            .into(binding.imagePokemon)

        binding.textPokemonName.text = pokemon.name?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
        binding.textPokemonNumber.text = "#${index + 1}"
        binding.container.setBackgroundColor(Color.parseColor(pokemon.primaryTypeColor))

        binding.root.setOnClickListener {
            listener?.onClick(pokemon, index)
        }
    }
}