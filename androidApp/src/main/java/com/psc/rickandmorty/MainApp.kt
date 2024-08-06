package com.psc.rickandmorty

import android.app.Application
import com.psc.rickandmorty.app.KoinInitializer

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDI()
    }

    private fun setupDI() {
        KoinInitializer(this).init()
    }
}