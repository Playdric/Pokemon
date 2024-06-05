package com.example.pokemon.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ContentState : Parcelable {
    @Parcelize
    data object Base : ContentState()

    @Parcelize
    data object Loading : ContentState()

    @Parcelize
    data class Error(val exception: Throwable) : ContentState()
}

abstract class AbstractViewState(open val state: ContentState) : Parcelable
abstract class AbstractViewSideEffect