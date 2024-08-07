package com.psc.rickandmorty.feature.home.presentation.ui.route

sealed interface HomeEvent {
    data object OnCharacterListEndReached : HomeEvent
}