package com.psc.rickandmorty.feature.home.presentation.ui.route

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.common.domain.util.Consts.PAGE_SIZE
import com.psc.rickandmorty.core.common.presentation.util.koinViewModel

@Composable
fun HomeRoute(
    onNavigateToDetails: (Character) -> Unit
) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()
    val lazyGridState = rememberLazyGridState()
    val isReachingEndOfCharacterList by remember {
        derivedStateOf {
            val currentCharactersCount = state.characters.size
            val visibleItemsInfo = lazyGridState.layoutInfo.visibleItemsInfo
            val lastDisplayedCharacterIndex = visibleItemsInfo.lastOrNull()?.index

            if (lastDisplayedCharacterIndex != null) {
                lastDisplayedCharacterIndex >= currentCharactersCount - (PAGE_SIZE/2)
            } else false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                is HomeUiEvent.NavigateToCharacterDetails -> onNavigateToDetails(it.character)
            }
        }
    }

    LaunchedEffect(isReachingEndOfCharacterList) {
        if (isReachingEndOfCharacterList) {
            viewModel.onEvent(HomeEvent.OnCharacterListEndReached)
        }
    }

    HomeScreen(
        state = state,
        lazyGridState = lazyGridState
    )
}