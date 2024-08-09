package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.data.model.response.EpisodeResponse
import com.psc.rickandmorty.core.common.data.model.response.EpisodesPageResponse
import com.psc.rickandmorty.core.common.data.model.response.PageInfoResponse
import com.psc.rickandmorty.core.common.data.service.EpisodeService
import com.psc.rickandmorty.core.common.data.util.MockUtils
import com.psc.rickandmorty.core.common.domain.model.Episode
import com.psc.rickandmorty.core.common.domain.util.Consts.PAGE_SIZE
import dev.mokkery.answering.calls
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EpisodeRemoteDataSourceImplTest {

    private val service by lazy { mock<EpisodeService>() }
    private val datasource by lazy { EpisodeRemoteDataSourceImpl(service) }

    @Test
    fun `when calls getAllEpisodes then request all pages to service`() = runTest {
        val expected = getEpisodes()
        val pagesResponses = getPagesResponses()

        everySuspend { service.getPage(any()) } calls { (page: Int) -> pagesResponses[page.dec()] }
        val result = datasource.getAllEpisodes()

        assertEquals(result, expected)
        repeat(TOTAL_PAGES) { page ->
            verifySuspend {
                service.getPage(page.inc())
            }
        }
    }

    private fun getEpisodes(): List<Episode> {
        val episodes = mutableListOf<Episode>()
        repeat(TOTAL_EPISODES) { episodes.add(MockUtils.episode) }

        return episodes
    }

    private fun getPagesResponses(): List<EpisodesPageResponse> {
        val pages = mutableListOf<EpisodesPageResponse>()
        repeat(TOTAL_PAGES) {
            pages.add(
                EpisodesPageResponse(
                    infoResponse = PageInfoResponse(
                        count = TOTAL_EPISODES,
                        pages = TOTAL_PAGES
                    ),
                    results = getEpisodesResponse()
                )
            )
        }

        return pages
    }

    private fun getEpisodesResponse(): List<EpisodeResponse> {
        val episodesResponse = mutableListOf<EpisodeResponse>()
        repeat(PAGE_SIZE) { episodesResponse.add(MockUtils.episodeResponse) }

        return episodesResponse
    }

    private companion object {
        const val TOTAL_PAGES = 3
        const val TOTAL_EPISODES = PAGE_SIZE * TOTAL_PAGES
    }

}