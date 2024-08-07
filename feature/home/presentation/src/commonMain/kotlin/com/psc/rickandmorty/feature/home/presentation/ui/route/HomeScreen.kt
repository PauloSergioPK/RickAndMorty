package com.psc.rickandmorty.feature.home.presentation.ui.route

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psc.rickandmorty.core.designsystem.util.Dimens
import com.psc.rickandmorty.feature.home.presentation.ui.component.CharacterCard

@Composable
internal fun HomeScreen(state: HomeState) {
    val lazyGridState = rememberLazyGridState()

    Scaffold {
        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(Dimens.default),
            horizontalArrangement = Arrangement.spacedBy(Dimens.default)
        ) {
            items(state.characters) {
                CharacterCard(
                    character = it,
                )
            }
        }
    }
}