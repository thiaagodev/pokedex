package com.thiaagodev.pokedex.service.repository

import androidx.lifecycle.liveData
import com.thiaagodev.pokedex.service.model.ResultAPI
import com.thiaagodev.pokedex.service.model.Pokemon
import com.thiaagodev.pokedex.service.repository.remote.PokemonService

class PokemonRepository(private val service: PokemonService): BaseRepository() {

    fun getAll() = liveData<ResultAPI<List<Pokemon>?>> {
        val result = execute(service.getAll())

        emit(result)
    }
}