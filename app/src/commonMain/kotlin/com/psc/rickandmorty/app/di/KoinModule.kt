package com.psc.rickandmorty.app.di

import com.psc.rickandmorty.core.common.data.di.commonDataModule
import com.psc.rickandmorty.core.common.domain.di.commonDomainModule
import com.psc.rickandmorty.feature.home.presentation.di.homePresentationModule
import com.psc.rickandmorty.feature.splash.presentation.di.splashPresentationModule
import org.koin.dsl.module

val appModules = module {
    includes(
        listOf(
            commonDataModule,
            commonDomainModule,
            homePresentationModule,
            splashPresentationModule
        )
    )
}