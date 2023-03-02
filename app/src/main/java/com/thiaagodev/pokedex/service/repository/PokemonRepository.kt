package com.thiaagodev.pokedex.service.repository

import androidx.lifecycle.liveData
import com.thiaagodev.pokedex.service.model.Pokemon
import com.thiaagodev.pokedex.service.model.ResultAPI
import com.thiaagodev.pokedex.service.repository.remote.PokemonService

class PokemonRepository(private val service: PokemonService) : BaseRepository() {

    suspend fun getAll() = liveData {
        val result = execute(service.getAll())

        emit(result)
    }

    suspend fun getDetails(pokemons: List<Pokemon>) = liveData {
        val pokemonList = mutableListOf<Pokemon>()
        pokemons.map {
            val pokemonResult = execute(service.getData(it.name))

            if (pokemonResult is ResultAPI.Success) {
                pokemonResult.data?.let { it1 -> pokemonList.add(it1) }
            }
        }

        emit(pokemonList)

    }
}