package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.data.mapper.toCharacter
import com.psc.rickandmorty.core.common.data.service.CharactersService
import com.psc.rickandmorty.core.common.data.service.EpisodeService
import com.psc.rickandmorty.core.common.data.service.LocationService
import com.psc.rickandmorty.core.common.domain.model.Character

internal class CharactersRemoteDataSourceImpl(
    private val charactersService: CharactersService,
    private val episodeService: EpisodeService,
    private val locationService: LocationService
) : CharactersRemoteDataSource {
    override suspend fun getPage(page: Int): List<Character> {
        val pageResponse = charactersService.getPage(page)
        return pageResponse.results.mapNotNull { character ->
            runCatching {
                val originLocationId = getIdFromUrl(character.originReference.url)
                val lastKnownLocationId = getIdFromUrl(character.lastKnownLocationReference.url)
                val episodesIds = character.episodesReferences.map { getIdFromUrl(it) }

                val originLocationResponse = locationService.getLocation(originLocationId)
                val lastKnownLocationResponse = locationService.getLocation(lastKnownLocationId)
                val episodes = episodesIds.map { episodeService.getEpisode(it) }

                character.toCharacter(
                    originLocationResponse = originLocationResponse,
                    lastKnownLocationResponse = lastKnownLocationResponse,
                    episodesResponses = episodes
                )
            }.getOrNull()
        }
    }

    private fun getIdFromUrl(url: String): Int {
        return url.split("/").last().toInt()
    }

}