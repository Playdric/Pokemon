package com.example.pokemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.local.model.Pokemon
import com.example.pokemon.data.remote.service.PokemonService
import com.example.pokemon.data.repository.PokemonRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class PokemonViewModel(
    private val pokemonRepository: PokemonRepository
): ViewModel() {

    val pokemons: StateFlow<List<Pokemon>> = pokemonRepository.pokemonList

    fun getPokemonPagedList() {
        viewModelScope.launch {
            pokemonRepository.getPokemonList()
        }
    }

    data class PokemonState(
        var pokemons: List<Any>
    )
}