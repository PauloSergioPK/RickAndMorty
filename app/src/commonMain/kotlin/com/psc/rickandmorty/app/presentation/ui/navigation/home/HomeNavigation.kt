package com.psc.rickandmorty.app.presentation.ui.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.psc.rickandmorty.app.presentation.model.Routes
import com.psc.rickandmorty.feature.home.presentation.ui.route.HomeRoute

fun NavGraphBuilder.homeNavigation(navController: NavController) {
    composable(Routes.HOME.name) {
        HomeRoute(
            onNavigateToDetails = {

            }
        )
    }
}

fun NavController.navigateToHome() {
    navigate(Routes.HOME.name)
}