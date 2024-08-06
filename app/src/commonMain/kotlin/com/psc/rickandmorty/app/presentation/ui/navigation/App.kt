package com.psc.rickandmorty.app.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import com.psc.rickandmorty.app.presentation.model.Routes
import com.psc.rickandmorty.app.presentation.ui.navigation.home.homeNavigation
import com.psc.rickandmorty.core.designsystem.theme.AppTheme
import com.psc.rickandmorty.core.designsystem.util.getAsyncImageLoader
import org.koin.compose.KoinContext

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App() {
    KoinContext {
        AppTheme {
            setSingletonImageLoaderFactory { getAsyncImageLoader(it) }
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Routes.HOME.name,
                modifier = Modifier.fillMaxSize(),
            ) {
                homeNavigation(navController)
            }
        }
    }
}