package com.psc.rickandmorty.app.presentation.ui.navigation.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.psc.rickandmorty.app.presentation.model.Routes
import com.psc.rickandmorty.app.presentation.ui.navigation.home.navigateToHome
import com.psc.rickandmorty.feature.splash.presentation.ui.route.SplashRoute

fun NavGraphBuilder.splashNavigation(navController: NavController) {
    composable(Routes.SPLASH.name) {
        SplashRoute(
            onNavigateToHome = {
                navController.popBackStack()
                navController.navigateToHome()
            }
        )
    }
}