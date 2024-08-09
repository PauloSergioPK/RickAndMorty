package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.data.datasource.local.EpisodeLocalDataSource
import com.psc.rickandmorty.core.common.data.datasource.local.LocationLocalDataSource
import com.psc.rickandmorty.core.common.data.mapper.toCharacter
import com.psc.rickandmorty.core.common.data.service.CharactersService
import com.psc.rickandmorty.core.common.domain.model.CharactersPage

internal class CharactersRemoteDataSourceImpl(
    private val charactersService: CharactersService,
    private val episodesLocalDataSource: EpisodeLocalDataSource,
    private val locationLocalDataSource: LocationLocalDataSource
) : CharactersRemoteDataSource {
    override suspend fun getPage(page: Int): CharactersPage {
        val pageResponse = charactersService.getPage(page)
        val totalPages = pageResponse.infoResponse.pages
        val remainingPages = totalPages - page

        val characters = pageResponse.results.map { character ->
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

        return CharactersPage(
            page = page,
            remainingPages = remainingPages,
            characters = characters
        )
    }

    private fun getIdFromUrl(url: String): Int? {
        return url.split("/").last().toIntOrNull()
    }

}