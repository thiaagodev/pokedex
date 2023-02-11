package com.thiaagodev.pokedex.di

import com.thiaagodev.pokedex.service.repository.PokemonRepository
import com.thiaagodev.pokedex.service.repository.remote.PokemonService
import com.thiaagodev.pokedex.ui.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val module = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    factory<PokemonService> { get<Retrofit>().create(PokemonService::class.java) }

    factory<PokemonRepository> { PokemonRepository(get()) }

    viewModel<PokemonViewModel> { PokemonViewModel(get()) }
}