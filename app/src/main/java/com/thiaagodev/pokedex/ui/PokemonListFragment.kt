package com.thiaagodev.pokedex.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thiaagodev.pokedex.databinding.FragmentPokemonListBinding
import com.thiaagodev.pokedex.service.model.ResultAPI
import com.thiaagodev.pokedex.ui.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PokemonViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAll()
        observe()
    }

    private fun observe() {
        viewModel.pokemonList.observe(viewLifecycleOwner) {
            if (it is ResultAPI.Success) {
                it.data?.results?.let { pokemons ->
                    viewModel.getDetails(pokemons)
                }
            }
        }

        viewModel.pokemonsDetails.observe(viewLifecycleOwner) {
            Log.d("Pokemon List", "$it")
        }
    }


}