package com.thiaagodev.pokedex.ui.listeners

import com.thiaagodev.pokedex.service.model.Pokemon

interface PokemonListener {
    fun onClick(pokemon: Pokemon, index: Int)
}