package com.example.pokemon.data.remote.service

import com.example.pokemon.data.local.model.Pokemon
import com.example.pokemon.data.remote.model.PokemonListResponse
import com.example.pokemon.data.remote.model.PokemonResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("offset") offset: Int = 0, @Query("limit") limit: Int = 20): PokemonListResponse

    @GET("pokemon/{id}/")
    suspend fun getPokemon(@Path("id") id: Int): PokemonResponse

}