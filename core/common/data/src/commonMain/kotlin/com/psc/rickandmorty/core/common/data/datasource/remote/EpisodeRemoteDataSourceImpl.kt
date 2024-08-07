package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.data.mapper.toEpisode
import com.psc.rickandmorty.core.common.data.service.EpisodeService
import com.psc.rickandmorty.core.common.domain.model.Episode
import com.psc.rickandmorty.core.common.domain.util.Consts.FIRST_PAGE
import com.psc.rickandmorty.core.common.domain.util.Consts.PAGE_SIZE

internal class EpisodeRemoteDataSourceImpl(
    private val episodeService: EpisodeService
) : EpisodeRemoteDataSource {
    override suspend fun getAllEpisodes(): List<Episode> {
        val allEpisodes = mutableListOf<Episode>()
        var currentPage = FIRST_PAGE
        var currentPageItemsCount = PAGE_SIZE

        while (currentPageItemsCount == PAGE_SIZE) {
            val episodesPage = getEpisodesPage(currentPage)
            allEpisodes.addAll(episodesPage)
            currentPageItemsCount = episodesPage.size
            currentPage++
        }

        return allEpisodes
    }

    private suspend fun getEpisodesPage(page: Int): List<Episode> {
        return episodeService.getPage(page).results.map { it.toEpisode() }
    }

}