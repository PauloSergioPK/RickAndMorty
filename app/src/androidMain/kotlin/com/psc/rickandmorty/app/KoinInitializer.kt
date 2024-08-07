package com.psc.rickandmorty.app

import android.content.Context
import com.psc.rickandmorty.core.common.data.di.commonDataModule
import com.psc.rickandmorty.core.common.domain.di.commonDomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

actual class KoinInitializer(
    private val context: Context
) {
    actual fun init() {
        startKoin {
            androidContext(context)
            modules(
                listOf(
                    commonDataModule,
                    commonDomainModule
                )
            )
        }
    }
}