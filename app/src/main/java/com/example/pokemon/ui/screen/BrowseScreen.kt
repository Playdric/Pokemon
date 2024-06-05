@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pokemon.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokemon.R
import com.example.pokemon.data.local.model.Pokemon
import com.example.pokemon.presentation.viewmodel.PokemonViewModel
import com.example.pokemon.ui.theme.PokemonTheme
import com.example.pokemon.ui.theme.largePadding
import com.example.pokemon.ui.theme.mediumPadding
import org.koin.androidx.compose.koinViewModel

@Composable
fun BrowseScreen(viewModel: PokemonViewModel = koinViewModel()) {

    val list = viewModel.pokemons.collectAsState()
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()
    var currentPokemon by rememberSaveable { mutableStateOf<Pokemon?>(null) }


    LaunchedEffect(null) {
        viewModel.getPokemonPagedList()
    }
    currentPokemon?.let {
        PokemonModal(pokemon = it) {
            currentPokemon = null
        }
    }
    Column {
        LazyVerticalGrid(
            state = listState,
            columns = GridCells.Adaptive(minSize = 100.dp),
            contentPadding = PaddingValues(mediumPadding),
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalArrangement = Arrangement.spacedBy(mediumPadding)
        ) {
            itemsIndexed(list.value) { index, item ->
                PokemonItem(pokemon = item) { pokemon -> currentPokemon = pokemon }
            }
        }

    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, onClick: ((pokemon: Pokemon) -> Unit)? = null) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainer
    ) {

        Column(
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = { onClick?.invoke(pokemon) }
                )
                .padding(mediumPadding)
        ) {
            AsyncImage(
                model = pokemon.sprites?.other?.officialArtwork?.frontDefault,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_catching_pokemon_24)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = pokemon.name
            )
        }
    }
}

@Composable
fun PokemonModal(pokemon: Pokemon, onDismiss: (() -> Unit)? = null) {
    val currentPokemonBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        sheetState = currentPokemonBottomSheetState,
        shape = BottomSheetDefaults.ExpandedShape,
        onDismissRequest = { onDismiss?.invoke() }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(150.dp)
                        .padding(start = mediumPadding),
                    model = pokemon.sprites?.other?.officialArtwork?.frontDefault,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.ic_catching_pokemon_24)
                )
                Column {
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge,
                        text = pokemon.name
                    )
                    Spacer(modifier = Modifier.weight(1F))
                }
            }
            Spacer(modifier = Modifier.height(mediumPadding))
            HorizontalDivider()
            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                Column(
                    modifier = Modifier
                        .weight(1F)
                        .padding(top = mediumPadding)
                ) {
                    pokemon.stats.forEach {
                        Row() {
                            Spacer(modifier = Modifier.weight(1F))
                            Text(text = it.name)
                            Spacer(modifier = Modifier.width(mediumPadding))
                            Text(text = it.base.toString())
                            Spacer(modifier = Modifier.width(mediumPadding))
                        }
                    }
                    Spacer(modifier = Modifier.height(mediumPadding))
                }
                VerticalDivider(modifier = Modifier.fillMaxHeight())
                Column(
                    modifier = Modifier
                        .weight(1F)
                        .padding(start = mediumPadding)
                ) {
                    Row(
                        modifier = Modifier.weight(1F),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_numbers_24), contentDescription = null)
                        Text(text = stringResource(id = R.string.pokemon_info_order))
                        Spacer(modifier = Modifier.width(mediumPadding))
                        Text(text = pokemon.pokemonId.toString())
                    }
                    Row(
                        modifier = Modifier.weight(1F),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_height_24), contentDescription = null)
                        Text(text = stringResource(id = R.string.pokemon_info_height))
                        Spacer(modifier = Modifier.width(mediumPadding))
                        Text(text = stringResource(id = R.string.centimeters, pokemon.height))
                    }
                    Row(
                        modifier = Modifier.weight(1F),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_balance_24), contentDescription = null)
                        Text(text = stringResource(id = R.string.pokemon_info_weight))
                        Spacer(modifier = Modifier.width(mediumPadding))
                        Text(text = stringResource(id = R.string.weight, pokemon.weight))
                    }
                }
            }
            Spacer(modifier = Modifier.height(largePadding))
        }
    }
}

@Composable
fun PokemonInfoRow(
    icon: Painter,
    stat: String,
    value: String
) {
    Row(modifier = Modifier.padding(mediumPadding, bottom = mediumPadding)) {
        Icon(painter = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(mediumPadding))
        Text(text = stat)
        Spacer(modifier = Modifier.width(mediumPadding))
        Text(text = value)
    }
}

@Preview
@Composable
fun PokemonItemPreview() {
    PokemonTheme {
        Surface(
            modifier = Modifier
                .height(500.dp)
                .width(500.dp),
            color = Color.White
        ) {

        }
    }
}