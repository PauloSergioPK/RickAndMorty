package com.psc.rickandmorty.app.presentation.ui

import androidx.compose.ui.window.ComposeUIViewController
import com.psc.rickandmorty.app.KoinInitializer
import com.psc.rickandmorty.app.presentation.ui.navigation.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) { App() }