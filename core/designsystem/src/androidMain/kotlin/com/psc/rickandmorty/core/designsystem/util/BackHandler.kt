package com.psc.rickandmorty.core.designsystem.util

import androidx.compose.runtime.Composable

@Composable
actual fun BackHandler(action: () -> Unit) {
    androidx.activity.compose.BackHandler { action() }
}