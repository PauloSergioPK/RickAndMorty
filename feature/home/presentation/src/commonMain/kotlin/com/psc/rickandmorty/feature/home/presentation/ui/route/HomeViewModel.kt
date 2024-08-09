package com.psc.rickandmorty.feature.home.presentation.ui.route

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psc.rickandmorty.core.common.domain.usecase.GetCharactersPageUseCase
import com.psc.rickandmorty.core.common.domain.util.Consts.FIRST_PAGE
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCharactersPageUseCase: GetCharactersPageUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<HomeUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var pageToLoad = FIRST_PAGE
    private var hasLoadedAllPages = false

    init {
        fetchCharacters(pageToLoad)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnCharacterListEndReached -> {
                fetchCharacters(pageToLoad)
            }
        }
    }

    private fun fetchCharacters(page: Int) {
        viewModelScope.launch {
            if (!hasLoadedAllPages && !uiState.value.isLoading) {
                _uiState.update { it.copy(isLoading = true) }

                val result = getCharactersPageUseCase(page)
                val charactersPage = result.getOrNull()

                if (charactersPage != null) {
                    val remainingPages = charactersPage.remainingPages
                    val pageCharacters = charactersPage.characters

                    if (remainingPages == 0) {
                        hasLoadedAllPages = true
                    } else {
                        pageToLoad++
                    }

                    _uiState.update {
                        it.copy(
                            characters = it.characters.toMutableList().plus(pageCharacters),
                            isLoading = false
                        )
                    }
                } else {
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}