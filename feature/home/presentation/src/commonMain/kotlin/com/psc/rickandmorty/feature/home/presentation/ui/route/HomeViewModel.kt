package com.psc.rickandmorty.feature.home.presentation.ui.route

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psc.rickandmorty.core.common.domain.usecase.GetCharactersPageUseCase
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

    private var currentPage = 1
    private var hasLoadedAllPages = false

    init {
        fetchCharacters(currentPage)
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.OnCharacterListEndReached -> {
                fetchCharacters(currentPage)
            }
        }
    }

    private fun fetchCharacters(page: Int) {
        viewModelScope.launch {
            if(!hasLoadedAllPages) {
                _uiState.update { it.copy(isLoading = true) }

                val result = getCharactersPageUseCase(page)
                val pageCharacters = result.getOrNull() ?: listOf()

                if(pageCharacters.size < PAGE_COUNT) {
                    hasLoadedAllPages = true
                }

                currentPage++

                _uiState.update {
                    it.copy(
                        characters = it.characters.toMutableList().plus(pageCharacters),
                        isLoading = false
                    )
                }
            }
        }
    }

    private companion object {
        const val PAGE_COUNT = 20
    }
}