package com.thiaagodev.pokedex.ui.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiaagodev.pokedex.service.model.Pokemon
import com.thiaagodev.pokedex.service.model.PokemonResponse
import com.thiaagodev.pokedex.service.model.ResultAPI
import com.thiaagodev.pokedex.service.repository.PokemonRepository
import kotlinx.coroutines.launch
import observeOnce

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    var selectedPokemonIndex: Int? = null

    private val _pokemonList = MutableLiveData<ResultAPI<PokemonResponse?>>()
    val pokemonList: LiveData<ResultAPI<PokemonResponse?>> = _pokemonList

    private val _pokemonsDetails = MutableLiveData<MutableList<Pokemon>>()
    val pokemonsDetails: LiveData<MutableList<Pokemon>> = _pokemonsDetails

    private val _selectedPokemon = MutableLiveData<Pokemon>()
    val selectedPokemon: LiveData<Pokemon> = _selectedPokemon

    fun getAll() {
        viewModelScope.launch {
            val result = repository.getAll()
            result.observeOnce {
                _pokemonList.value = it
            }
        }
    }

    fun getDetails(pokemons: List<Pokemon>) {
        viewModelScope.launch {
            val result = repository.getDetails(pokemons)
            result.observeOnce {
                _pokemonsDetails.value = it
            }
        }
    }

    fun setSelectedPokemon(selectedPokemon: Pokemon) {
        _selectedPokemon.value = selectedPokemon
    }
}

