package com.psc.rickandmorty.feature.home.presentation.ui.route

import androidx.lifecycle.ViewModel
import com.psc.rickandmorty.core.common.domain.usecase.GetCharactersPageUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class HomeViewModel(
    private val getCharactersPageUseCase: GetCharactersPageUseCase
) : ViewModel() {

    private val _uiEvent = Channel<HomeUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


}