package com.psc.rickandmorty.feature.splash.presentation.ui.route

sealed interface SplashUiEvent {
    data object NavigateToHome : SplashUiEvent
}