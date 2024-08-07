package com.psc.rickandmorty.feature.home.presentation.ui.route

import com.psc.rickandmorty.core.common.domain.model.Character

sealed interface HomeUiEvent {
    data class NavigateToCharacterDetails(
        val character: Character
    ) : HomeUiEvent
}