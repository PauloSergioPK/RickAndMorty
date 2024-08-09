package com.psc.rickandmorty.app.presentation.ui.navigation.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.psc.rickandmorty.app.presentation.model.Routes
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.feature.details.presentation.ui.route.DetailsRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.thauvin.erik.urlencoder.UrlEncoderUtil

private const val CHARACTER = "character"

fun NavGraphBuilder.detailsNavigation(navController: NavController) {
    composable(
        route = Routes.DETAILS.name.plus("/{${CHARACTER}}"),
        arguments = listOf(
            navArgument(CHARACTER) {
                type = NavType.StringType
            }
        )
    ) { entry ->
        val encodedCharacterJson = entry.arguments?.getString(CHARACTER).orEmpty()
        val characterJson = UrlEncoderUtil.decode(encodedCharacterJson)
        val character = Json.decodeFromString<Character>(characterJson)

        DetailsRoute(
            character = character,
            onNavigateBack = navController::navigateUp
        )
    }
}

fun NavController.navigateToDetails(character: Character) {
    val json = Json.encodeToString(character)
    val encodedJson = UrlEncoderUtil.encode(json)
    navigate(Routes.DETAILS.name.plus("/$encodedJson")) {
        launchSingleTop = true
    }
}