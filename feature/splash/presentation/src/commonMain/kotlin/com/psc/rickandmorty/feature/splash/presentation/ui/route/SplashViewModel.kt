package com.psc.rickandmorty.feature.splash.presentation.ui.route

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psc.rickandmorty.core.common.domain.usecase.FetchEpisodesUseCase
import com.psc.rickandmorty.core.common.domain.usecase.FetchLocationsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val fetchEpisodesUseCase: FetchEpisodesUseCase,
    private val fetchLocationsUseCase: FetchLocationsUseCase
) : ViewModel() {

    private val _uiEvent = Channel<SplashUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        fetchEpisodesAndLocations()
    }

    private fun fetchEpisodesAndLocations() {
        viewModelScope.launch {
            val episodesFetchResult = async { fetchEpisodesUseCase() }
            val locationsFetchResult = async { fetchLocationsUseCase() }

            awaitAll(episodesFetchResult, locationsFetchResult)
            _uiEvent.send(SplashUiEvent.NavigateToHome)
        }
    }
}