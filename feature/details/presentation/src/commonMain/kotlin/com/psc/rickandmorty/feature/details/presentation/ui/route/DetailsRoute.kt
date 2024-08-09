package com.psc.rickandmorty.feature.details.presentation.ui.route

import androidx.compose.runtime.Composable
import com.psc.rickandmorty.core.common.domain.model.Character

@Composable
fun DetailsRoute(
    character: Character,
    onNavigateBack: () -> Unit
) {
    DetailsScreen(
        character = character,
        onNavigateBackClicked = onNavigateBack
    )
}