package com.thiaagodev.pokedex.service.repository.remote

import com.thiaagodev.pokedex.service.model.Pokemon
import com.thiaagodev.pokedex.service.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("pokemon?limit=1300&offset=0")
    suspend fun getAll(): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getData(@Path("name") name: String?): Response<Pokemon>
}