package com.example.pokemon.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count")
    var count: Int? = null,
    @SerializedName("next")
    var next: String? = null,
    @SerializedName("previous")
    var previous: String? = null,
    @SerializedName("results")
    var results: ArrayList<PokemonResult> = arrayListOf()
)

data class PokemonResult(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url: String? = null
)