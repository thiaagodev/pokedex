package com.thiaagodev.pokedex.service.model

import com.google.gson.annotations.SerializedName

class SimplePokemon {
    @SerializedName("name")
    lateinit var name: String

    @SerializedName("url")
    lateinit var url: String
}