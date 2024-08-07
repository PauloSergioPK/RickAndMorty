package com.psc.rickandmorty.core.common.presentation.util

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.currentKoinScope
import org.koin.core.parameter.ParametersHolder
import org.koin.core.parameter.parametersOf

@Composable
inline fun <reified T : ViewModel> koinViewModel(parameters: ParametersHolder? = null): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>(parameters = { parameters ?: parametersOf() })
    }
}