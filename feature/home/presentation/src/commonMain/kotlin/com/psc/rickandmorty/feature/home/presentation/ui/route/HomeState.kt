package com.psc.rickandmorty.feature.home.presentation.ui.route

import com.psc.rickandmorty.core.common.domain.model.Character

data class HomeState(
    val characters: List<Character> = listOf(),
    val isLoading: Boolean = false
)