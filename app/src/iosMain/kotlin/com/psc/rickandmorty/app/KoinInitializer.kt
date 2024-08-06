package com.psc.rickandmorty.app

import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(listOf())
        }
    }
}