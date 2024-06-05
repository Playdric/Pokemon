package com.example.pokemon.core

import com.example.pokemon.data.remote.service.PokemonService
import com.example.pokemon.data.repository.PokemonRepository
import com.example.pokemon.presentation.viewmodel.PokemonViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    singleOf(::provideOkHttpClient)
    singleOf(::provideRetrofit)

    // ViewModels
    viewModelOf(::PokemonViewModel)

    // Repositories
    singleOf(::PokemonRepository)

    // Services
    single<PokemonService> { provideService(get())}

}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()
}

private fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

inline fun <reified T> provideService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}