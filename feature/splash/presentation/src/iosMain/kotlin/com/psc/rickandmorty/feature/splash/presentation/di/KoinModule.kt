package com.psc.rickandmorty.feature.splash.presentation.di

import com.psc.rickandmorty.feature.splash.presentation.ui.route.SplashViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val splashPresentationModule = module {
    factoryOf(::SplashViewModel)
}