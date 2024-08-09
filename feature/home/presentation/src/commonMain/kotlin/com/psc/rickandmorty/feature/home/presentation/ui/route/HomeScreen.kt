package com.psc.rickandmorty.feature.home.presentation.ui.route

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.common.domain.util.Consts.PAGE_SIZE
import com.psc.rickandmorty.core.designsystem.util.Dimens
import com.psc.rickandmorty.feature.home.presentation.ui.component.CharacterCard
import com.psc.rickandmorty.feature.home.presentation.ui.component.CharacterLoadingCard

@Composable
internal fun HomeScreen(
    state: HomeState,
    lazyGridState: LazyGridState,
    onCharacterClicked: (Character) -> Unit
) {
    Scaffold {
        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(Dimens.default),
            horizontalArrangement = Arrangement.spacedBy(Dimens.default),
            contentPadding = PaddingValues(horizontal = Dimens.bigAlt, vertical = Dimens.huge)
        ) {
            items(
                items = state.characters,
                key = { it.id }
            ) {
                CharacterCard(
                    character = it,
                    modifier = Modifier.clickable(onClick = { onCharacterClicked(it) })
                )
            }
            if (state.isLoading) {
                items(count = PAGE_SIZE / 3) { CharacterLoadingCard() }
            }
        }
    }
}