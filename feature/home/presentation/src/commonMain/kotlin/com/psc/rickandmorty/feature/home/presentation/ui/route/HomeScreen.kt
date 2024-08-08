package com.psc.rickandmorty.feature.home.presentation.ui.route

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.psc.rickandmorty.core.designsystem.util.Dimens
import com.psc.rickandmorty.feature.home.presentation.ui.component.CharacterCard

@Composable
internal fun HomeScreen(
    state: HomeState,
    lazyGridState: LazyGridState
) {
    Scaffold {
        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(Dimens.default),
            horizontalArrangement = Arrangement.spacedBy(Dimens.default),
            contentPadding = PaddingValues(Dimens.bigAlt)
        ) {
            items(state.characters) {
                CharacterCard(character = it)
            }
        }
    }
}