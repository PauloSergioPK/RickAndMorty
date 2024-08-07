package com.psc.rickandmorty.feature.home.presentation.di

import com.psc.rickandmorty.feature.home.presentation.ui.route.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val homePresentationModule = module {
    factoryOf(::HomeViewModel)
}