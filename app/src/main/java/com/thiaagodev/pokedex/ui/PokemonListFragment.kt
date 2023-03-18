package com.thiaagodev.pokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thiaagodev.pokedex.R
import com.thiaagodev.pokedex.databinding.FragmentPokemonListBinding
import com.thiaagodev.pokedex.service.model.Pokemon
import com.thiaagodev.pokedex.service.model.ResultAPI
import com.thiaagodev.pokedex.ui.adapters.PokemonAdapter
import com.thiaagodev.pokedex.ui.listeners.PokemonListener
import com.thiaagodev.pokedex.ui.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PokemonViewModel by viewModel()
    private val adapter = PokemonAdapter()

    private val navController by lazy {
        findNavController()
    }

    private val parentActivity by lazy {
        requireActivity() as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerPokemonList.adapter = adapter
        adapter.attachListener(object : PokemonListener {
            override fun onClick(pokemon: Pokemon, index: Int) {
                viewModel.setSelectedPokemon(pokemon)
                viewModel.selectedPokemonIndex = index
                navController.navigate(R.id.action_pokemonListFragment_to_pokemonDetailsFragment)
            }
        })

        viewModel.getAll()
        observe()
    }

    override fun onResume() {
        super.onResume()
        parentActivity.apply {
            this.showToolbar()
        }
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
            binding.pokemonLoading.visibility = View.GONE
            adapter.updatePokemonList(it)
        }
    }


}