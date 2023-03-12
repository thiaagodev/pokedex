package com.thiaagodev.pokedex.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.thiaagodev.pokedex.databinding.FragmentPokemonDetailsBinding
import com.thiaagodev.pokedex.ui.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale


class PokemonDetailsFragment : Fragment() {

    private var _binding: FragmentPokemonDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PokemonViewModel by viewModel()
    private val navController by lazy {
        findNavController()
    }

    private val pokemon by lazy {
        viewModel.selectedPokemon.value
    }

    private val pokemonIndex by lazy {
        viewModel.selectedPokemonIndex
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        binding.textPokemonName.text = pokemon?.name?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }

        Glide.with(binding.root.context)
            .asGif()
            .load(pokemonIndex?.let { pokemon?.getImage(it + 1) })
            .into(binding.imagePokemon)

        binding.imageBackground.setBackgroundColor(Color.parseColor(pokemon?.primaryTypeColor))

    }


}