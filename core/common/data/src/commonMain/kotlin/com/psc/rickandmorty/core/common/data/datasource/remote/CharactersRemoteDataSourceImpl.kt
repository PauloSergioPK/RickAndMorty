package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.data.datasource.local.EpisodeLocalDataSource
import com.psc.rickandmorty.core.common.data.datasource.local.LocationLocalDataSource
import com.psc.rickandmorty.core.common.data.mapper.toCharacter
import com.psc.rickandmorty.core.common.data.service.CharactersService
import com.psc.rickandmorty.core.common.domain.model.Character

internal class CharactersRemoteDataSourceImpl(
    private val charactersService: CharactersService,
    private val episodesLocalDataSource: EpisodeLocalDataSource,
    private val locationLocalDataSource: LocationLocalDataSource
) : CharactersRemoteDataSource {
    override suspend fun getPage(page: Int): List<Character> {
        val pageResponse = charactersService.getPage(page)
        return pageResponse.results.mapNotNull { character ->
            runCatching {
                val originLocationId = getIdFromUrl(character.originReference.url)
                val lastKnownLocationId = getIdFromUrl(character.lastKnownLocationReference.url)
                val episodesIds = character.episodesReferences.map { getIdFromUrl(it) }

                val originLocation = locationLocalDataSource.getLocationById(originLocationId)
                val lastKnownLocation = locationLocalDataSource.getLocationById(lastKnownLocationId)
                val episodes = episodesIds.map { episodesLocalDataSource.getEpisodeById(it) }

                character.toCharacter(
                    originLocation = originLocation,
                    lastKnownLocation = lastKnownLocation,
                    episodes = episodes
                )
            }.getOrElse {
                println("===> erro $it")
                null
            }
        }
    }

    private fun getIdFromUrl(url: String): Int {
        return url.split("/").last().toInt()
    }

}