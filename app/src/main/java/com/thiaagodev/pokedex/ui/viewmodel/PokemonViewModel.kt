package com.thiaagodev.pokedex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.thiaagodev.pokedex.service.model.Pokemon
import com.thiaagodev.pokedex.service.model.PokemonResponse
import com.thiaagodev.pokedex.service.model.ResultAPI
import com.thiaagodev.pokedex.service.repository.PokemonRepository

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    fun getAll(): LiveData<ResultAPI<PokemonResponse?>> = repository.getAll()

    fun getDetails(pokemons: List<Pokemon>): LiveData<MutableList<Pokemon>> =
        repository.getDetails(pokemons)
}