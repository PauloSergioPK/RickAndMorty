package com.psc.rickandmorty.app

import com.psc.rickandmorty.app.di.appModules
import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin { modules(appModules) }
    }
}