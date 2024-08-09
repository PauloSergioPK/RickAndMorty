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
        return pageResponse.results.map { character ->
            val originLocationId = getIdFromUrl(character.originReference.url)
            val lastKnownLocationId = getIdFromUrl(character.lastKnownLocationReference.url)
            val episodesIds = character.episodesReferences.mapNotNull { getIdFromUrl(it) }

            val originLocation = originLocationId?.let {
                locationLocalDataSource.getLocationById(it)
            }

            val lastKnownLocation = lastKnownLocationId?.let {
                locationLocalDataSource.getLocationById(it)
            }

            val episodes = episodesIds.mapNotNull { episodesLocalDataSource.getEpisodeById(it) }

            character.toCharacter(
                originLocation = originLocation,
                lastKnownLocation = lastKnownLocation,
                episodes = episodes
            )
        }
    }

    private fun getIdFromUrl(url: String): Int? {
        return url.split("/").last().toIntOrNull()
    }

}