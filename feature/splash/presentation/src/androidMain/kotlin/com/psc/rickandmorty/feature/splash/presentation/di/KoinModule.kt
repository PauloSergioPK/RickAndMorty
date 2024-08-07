package com.psc.rickandmorty.feature.splash.presentation.di

import com.psc.rickandmorty.feature.splash.presentation.ui.route.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val splashPresentationModule = module {
    viewModelOf(::SplashViewModel)
}