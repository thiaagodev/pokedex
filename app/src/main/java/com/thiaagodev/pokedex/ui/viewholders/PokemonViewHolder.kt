package com.thiaagodev.pokedex.ui.viewholders

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.thiaagodev.pokedex.databinding.PokemonItemBinding
import com.thiaagodev.pokedex.service.model.Pokemon
import java.util.Locale

class PokemonViewHolder(private val binding: PokemonItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pokemon: Pokemon, index: Int) {
        binding.textPokemonName.text = pokemon.name?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }

        Picasso.get()
            .load(pokemon.getImage(index + 1))
            .into(binding.imagePokemon)
        binding.textPokemonNumber.text = "#${index + 1}"
        binding.container.setBackgroundColor(Color.parseColor(pokemon.primaryTypeColor))
    }
}