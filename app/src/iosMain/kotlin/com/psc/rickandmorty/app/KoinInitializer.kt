package com.psc.rickandmorty.app

import com.psc.rickandmorty.core.common.data.di.commonDataModule
import com.psc.rickandmorty.core.common.domain.di.commonDomainModule
import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(
                listOf(
                    commonDataModule,
                    commonDomainModule
                )
            )
        }
    }
}