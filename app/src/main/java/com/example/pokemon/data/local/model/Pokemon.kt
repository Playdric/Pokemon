package com.example.pokemon.data.local.model

import com.example.pokemon.data.remote.model.Cries
import com.example.pokemon.data.remote.model.Sprites
import com.example.pokemon.data.remote.model.Stats
import com.example.pokemon.data.remote.model.Types
import java.io.Serializable

data class Pokemon(
    var pokemonId: Int,
    var cries: Cries? = null,
    var height: Int = 0,
    var weight: Float = 0F,
    var name: String,
    var sprites: Sprites? = null,
    var types: ArrayList<Types> = arrayListOf(),
    var stats: List<Stat> = listOf()
): Serializable

data class Stat(
    val name: String,
    val base: Int,
    val url: String,
)