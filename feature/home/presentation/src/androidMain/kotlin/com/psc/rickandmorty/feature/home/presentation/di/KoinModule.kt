package com.psc.rickandmorty.feature.home.presentation.di

import com.psc.rickandmorty.feature.home.presentation.ui.route.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val homePresentationModule = module {
    viewModelOf(::HomeViewModel)
}