package com.thiaagodev.pokedex.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.thiaagodev.pokedex.service.model.ResultAPI
import com.thiaagodev.pokedex.service.model.SimplePokemon
import com.thiaagodev.pokedex.service.repository.remote.PokemonService

class PokemonRepository(private val service: PokemonService): BaseRepository() {

    fun getAll() = liveData<ResultAPI<List<SimplePokemon>?>> {
        val result = execute(service.getAll())

        emit(result)
    }
}