package com.psc.rickandmorty.app.presentation.ui.navigation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.psc.rickandmorty.app.presentation.model.Routes

fun NavGraphBuilder.homeNavigation(navController: NavController) {
    composable(Routes.HOME.name) {
        Scaffold {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Home")
            }
        }
    }
}