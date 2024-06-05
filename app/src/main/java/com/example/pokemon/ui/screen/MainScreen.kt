package com.example.pokemon.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemon.R
import com.example.pokemon.ui.theme.PokemonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    PokemonTheme {
        var title by rememberSaveable { mutableIntStateOf(R.string.screen_title_browse) }
        var selectedNavigationItem by rememberSaveable { mutableIntStateOf(0) }
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = { Text(stringResource(id = title)) },
                    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                )
            },
            bottomBar = {
                val bottomNavScreens = listOf(BottomNavigationScreen.Browse, BottomNavigationScreen.Search)
                NavigationBar {
                    bottomNavScreens.forEachIndexed() { index, screen ->
                        NavigationBarItem(
                            selected = index == selectedNavigationItem,
                            onClick = {
                                title = screen.title
                                selectedNavigationItem = index
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(painter = painterResource(id = screen.icon), contentDescription = null) },
                            label = { Text(text = stringResource(id = screen.title)) }
                        )
                    }

                }
            }
        ) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = "browse"
            ) {
                composable("browse") {
                    BrowseScreen()
                }
                composable("search") {
                    SearchScreen()
                }
            }
        }
    }
}