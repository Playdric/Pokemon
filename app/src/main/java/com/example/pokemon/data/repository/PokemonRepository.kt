package com.example.pokemon.data.repository

import com.example.pokemon.data.local.model.Pokemon
import com.example.pokemon.data.local.model.Stat
import com.example.pokemon.data.remote.service.PokemonService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PokemonRepository(
    val pokemonService: PokemonService
) {

    private var _pokemonList: MutableStateFlow<List<Pokemon>> = MutableStateFlow(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    suspend fun getPokemonList() {
        try {
            println("COUCOU list size ${pokemonList.value.size}")
            val pokemonsList = pokemonService.getPokemonList(offset = pokemonList.value.size)
            val results = mutableListOf<Pokemon>()
            pokemonsList.results.forEach { pokemonResult ->
                val url = pokemonResult.url ?: return@forEach
                val split = url.split("/")
                val id = split[split.size - 2].toInt()
                val pokemon = pokemonService.getPokemon(id)
                results.add(
                    Pokemon(
                        pokemonId = pokemon.id ?: 0,
                        cries = pokemon.cries,
                        height = (pokemon.height?: 0) * 10,
                        weight = (pokemon.weight ?: 0).toFloat() / 10F,
                        name = pokemon.name?.replaceFirstChar { it.titlecase() } ?: "",
                        sprites = pokemon.sprites,
                        types = pokemon.types,
                        stats = pokemon.stats.map { Stat(
                            name = it.stat?.name ?: "",
                            base = it.baseStat ?: 0,
                            url = it.stat?.url ?: ""
                        ) }
                    )
                )
            }
            _pokemonList.emit(pokemonList.value + results)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}