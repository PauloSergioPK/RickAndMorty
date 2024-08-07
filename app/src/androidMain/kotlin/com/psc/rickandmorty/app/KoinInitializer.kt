package com.psc.rickandmorty.app

import android.content.Context
import com.psc.rickandmorty.app.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

actual class KoinInitializer(
    private val context: Context
) {
    actual fun init() {
        startKoin {
            androidContext(context)
            modules(appModules)
        }
    }
}