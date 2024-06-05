package com.example.pokemon.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.pokemon.R

sealed class BottomNavigationScreen(val route: String, @StringRes val title: Int, @DrawableRes val icon: Int) {
    data object Browse : BottomNavigationScreen("browse", R.string.screen_title_browse, R.drawable.ic_catching_pokemon_24)
    data object Search : BottomNavigationScreen("search", R.string.screen_title_search, R.drawable.ic_search_24)
}