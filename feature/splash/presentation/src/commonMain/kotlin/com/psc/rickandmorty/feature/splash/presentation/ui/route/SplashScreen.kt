package com.psc.rickandmorty.feature.splash.presentation.ui.route

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.psc.rickandmorty.core.designsystem.animation.JsonAnimation

@Composable
internal fun SplashScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)
    ) {
        JsonAnimation(
            jsonPath = ("files/anim_splash.json"),
            modifier = Modifier.fillMaxSize()
        )
    }
}