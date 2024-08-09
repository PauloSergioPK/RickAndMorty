package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.data.mapper.toEpisode
import com.psc.rickandmorty.core.common.data.service.EpisodeService
import com.psc.rickandmorty.core.common.domain.model.Episode
import com.psc.rickandmorty.core.common.domain.util.Consts.FIRST_PAGE
import com.psc.rickandmorty.core.common.domain.util.Consts.SECOND_PAGE

internal class EpisodeRemoteDataSourceImpl(
    private val episodeService: EpisodeService
) : EpisodeRemoteDataSource {
    override suspend fun getAllEpisodes(): List<Episode> {
        val allEpisodes = mutableListOf<Episode>()

        val firstPageResponse = episodeService.getPage(FIRST_PAGE)
        val totalPages = firstPageResponse.infoResponse.pages
        val firstPageEpisodes = firstPageResponse.results.map { it.toEpisode() }
        allEpisodes.addAll(firstPageEpisodes)

        for (page in SECOND_PAGE..totalPages) {
            val episodesPage = getEpisodesPage(page)
            allEpisodes.addAll(episodesPage)
        }

        return allEpisodes
    }

    private suspend fun getEpisodesPage(page: Int): List<Episode> {
        return episodeService.getPage(page).results.map { it.toEpisode() }
    }

}