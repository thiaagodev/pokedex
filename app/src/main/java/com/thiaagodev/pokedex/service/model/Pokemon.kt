package com.thiaagodev.pokedex.service.model

import com.google.gson.annotations.SerializedName

class PokemonResponse {
    @SerializedName("results")
    var results: List<Pokemon>? = null
}

class Pokemon {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("types")
    var types: List<PokemonType?>? = null

    @SerializedName("stats")
    var stats: List<PokemonStat?>? = null

    private val pokemonColorsByType: Map<String, String> = mapOf(
        Pair("normal", "#A8A77A"),
        Pair("fire", "#EE8130"),
        Pair("water", "#6390F0"),
        Pair("electric", "#F7D02C"),
        Pair("grass", "#7AC74C"),
        Pair("ice", "#96D9D6"),
        Pair("fighting", "#C22E28"),
        Pair("poison", "#A33EA1"),
        Pair("ground", "#E2BF65"),
        Pair("flying", "#A98FF3"),
        Pair("psychic", "#F95587"),
        Pair("bug", "#A6B91A"),
        Pair("rock", "#B6A136"),
        Pair("ghost", "#735797"),
        Pair("dragon", "#6F35FC"),
        Pair("dark", "#705746"),
        Pair("steel", "#B7B7CE"),
        Pair("fairy", "#D685AD"),
    )

    fun getImage(index: Int): String {
        val maxIndexWithGif = 649
        return if (index <= maxIndexWithGif) {
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/$index.gif"
        } else {
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/$index.png"
        }
    }

    val primaryTypeColor: String?
        get() {
            types?.let { typeList ->
                typeList[0]?.type.let {
                    return pokemonColorsByType[it?.name]
                }
            }

            return ""
        }
}

data class PokemonType(
    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: Type
)

data class Type(
    @SerializedName("name")
    val name: String
)

data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int,

    @SerializedName("stat")
    val stat: Stat
)

data class Stat(
    @SerializedName("name")
    val name: String
)