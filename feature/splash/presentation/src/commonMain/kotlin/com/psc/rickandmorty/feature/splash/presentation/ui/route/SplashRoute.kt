package com.psc.rickandmorty.feature.splash.presentation.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.psc.rickandmorty.core.common.presentation.util.koinViewModel

@Composable
fun SplashRoute(
    onNavigateToHome: () -> Unit
) {
    val viewModel: SplashViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                is SplashUiEvent.NavigateToHome -> onNavigateToHome()
            }
        }
    }

    SplashScreen()
}