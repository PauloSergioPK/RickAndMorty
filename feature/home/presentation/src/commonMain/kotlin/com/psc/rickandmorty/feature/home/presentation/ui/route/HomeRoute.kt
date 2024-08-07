package com.psc.rickandmorty.feature.home.presentation.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.common.presentation.util.koinViewModel

@Composable
fun HomeRoute(
    onNavigateToDetails: (Character) -> Unit
) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                is HomeUiEvent.NavigateToCharacterDetails -> onNavigateToDetails(it.character)
            }
        }
    }

    HomeScreen(state = state)
}