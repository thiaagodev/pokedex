package com.thiaagodev.pokedex.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.thiaagodev.pokedex.R
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

    private val parentActivity by lazy {
        requireActivity() as MainActivity
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

        parentActivity.apply {
            this.hideToolbar()
        }

        binding.textPokemonName.text = pokemon?.name?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }

        Glide.with(binding.root.context)
            .asGif()
            .load(pokemonIndex?.let { pokemon?.getImage(it + 1) })
            .into(binding.imagePokemon)

        val backgroundLayer = binding.imageBackground.background as GradientDrawable
        backgroundLayer.setColor(Color.parseColor(pokemon?.primaryTypeColor))

        val linearLayout = LinearLayout(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            gravity = Gravity.CENTER_HORIZONTAL
        }

        binding.typeChipGroup.addView(linearLayout)

        pokemon?.types?.forEach { type ->
            val teste = pokemon!!.pokemonColorsByType[type?.type?.name] ?: ""
            val chip = Chip(context)
            chip.text =
                type?.type?.name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            val color = Color.parseColor("#EE8130")
            val colorStateList = ColorStateList.valueOf(color)
            chip.chipBackgroundColor = colorStateList
            chip.setTextColor(resources.getColor(R.color.white))

            chip.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER_HORIZONTAL
                marginEnd = 24
            }

            linearLayout.addView(chip)
        }

    }


}