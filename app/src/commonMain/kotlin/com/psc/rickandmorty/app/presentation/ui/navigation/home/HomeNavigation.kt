package com.psc.rickandmorty.app.presentation.ui.navigation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.psc.rickandmorty.app.presentation.model.Routes
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.common.domain.usecase.FetchEpisodesUseCase
import com.psc.rickandmorty.core.common.domain.usecase.FetchLocationsUseCase
import com.psc.rickandmorty.core.common.domain.usecase.GetCharactersPageUseCase
import kotlinx.coroutines.async
import org.koin.compose.getKoin

fun NavGraphBuilder.homeNavigation(navController: NavController) {
    composable(Routes.HOME.name) {
        val getCharactersPageUseCase = getKoin().get<GetCharactersPageUseCase>()
        val fetchLocationsUseCase = getKoin().get<FetchLocationsUseCase>()
        val fetchEpisodesUseCase = getKoin().get<FetchEpisodesUseCase>()
        var characters: SnapshotStateList<Character> = remember { mutableStateListOf() }

        LaunchedEffect(Unit) {
            val fetch1 = async { fetchLocationsUseCase() }
            val fetch2 = async { fetchEpisodesUseCase() }

            println("===> locations ${fetch1.await()}")
            println("===> episodes ${fetch2.await()}")

            val result = getCharactersPageUseCase.invoke(1)
            characters = result.getOrNull()?.toMutableStateList() ?: mutableStateListOf()
            println("===> $result")
        }

        Scaffold {
            Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                characters.forEach {
                    Box(modifier = Modifier.size(200.dp).background(Color.Red)) {
                        Text(it.name)
                    }
                }
            }
        }
    }
}