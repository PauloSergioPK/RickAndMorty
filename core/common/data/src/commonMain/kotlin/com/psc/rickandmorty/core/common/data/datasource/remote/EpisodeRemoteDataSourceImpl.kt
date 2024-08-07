package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.data.mapper.toEpisode
import com.psc.rickandmorty.core.common.data.service.EpisodeService
import com.psc.rickandmorty.core.common.domain.model.Episode

internal class EpisodeRemoteDataSourceImpl(
    private val episodeService: EpisodeService
) : EpisodeRemoteDataSource {
    override suspend fun getAllEpisodes(): List<Episode> {
        val allEpisodes = mutableListOf<Episode>()
        var currentPage = FIRST_PAGE
        var currentPageItemsCount = PAGE_ITEMS_COUNT

        while (currentPageItemsCount == PAGE_ITEMS_COUNT) {
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

    private companion object {
        const val FIRST_PAGE = 1
        const val PAGE_ITEMS_COUNT = 20
    }

}