package com.thiaagodev.pokedex.service.repository.remote

import com.thiaagodev.pokedex.service.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon?limit=100000&offset=0")
    suspend fun getAll(): Response<List<Pokemon>>
}