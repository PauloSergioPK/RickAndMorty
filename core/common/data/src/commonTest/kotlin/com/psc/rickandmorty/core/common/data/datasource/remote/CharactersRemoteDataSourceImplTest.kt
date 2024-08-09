package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.data.datasource.local.EpisodeLocalDataSource
import com.psc.rickandmorty.core.common.data.datasource.local.LocationLocalDataSource
import com.psc.rickandmorty.core.common.data.model.response.CharacterResponse
import com.psc.rickandmorty.core.common.data.model.response.CharactersPageResponse
import com.psc.rickandmorty.core.common.data.model.response.PageInfoResponse
import com.psc.rickandmorty.core.common.data.service.CharactersService
import com.psc.rickandmorty.core.common.data.util.MockUtils
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.common.domain.model.CharactersPage
import com.psc.rickandmorty.core.common.domain.util.Consts.FIRST_PAGE
import com.psc.rickandmorty.core.common.domain.util.Consts.PAGE_SIZE
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CharactersRemoteDataSourceImplTest {

    private val charactersService by lazy { mock<CharactersService>() }
    private val episodesLocalDataSource by lazy { mock<EpisodeLocalDataSource>() }
    private val locationsLocalDataSource by lazy { mock<LocationLocalDataSource>() }
    private val datasource by lazy {
        CharactersRemoteDataSourceImpl(
            charactersService = charactersService,
            episodesLocalDataSource = episodesLocalDataSource,
            locationLocalDataSource = locationsLocalDataSource
        )
    }

    @Test
    fun `when calls getPage then returns a CharactersPage`() = runTest {
        val page = FIRST_PAGE
        val characters = getCharacters()
        val pageResponse = getPageResponse()
        val locationIds = getLocationIds()
        val episodeIds = getEpisodeIds()
        val expected = CharactersPage(
            page = page,
            remainingPages = TOTAL_PAGES - page,
            characters = characters
        )

        everySuspend { charactersService.getPage(any()) } returns pageResponse
        everySuspend { episodesLocalDataSource.getEpisodeById(any()) } returns MockUtils.episode
        everySuspend { locationsLocalDataSource.getLocationById(any()) } returns MockUtils.location
        val result = datasource.getPage(page)

        assertEquals(result, expected)
        verifySuspend {
            charactersService.getPage(page)
            locationIds.forEach { locationId ->
                locationId?.let { locationsLocalDataSource.getLocationById(it) }
            }
            episodeIds.forEach { episodeId ->
                episodeId?.let { episodesLocalDataSource.getEpisodeById(it) }
            }
        }
    }

    private fun getCharacters(): List<Character> {
        val characters = mutableListOf<Character>()
        repeat(PAGE_SIZE) { characters.add(MockUtils.character) }

        return characters
    }

    private fun getPageResponse(): CharactersPageResponse {
        return CharactersPageResponse(
            infoResponse = PageInfoResponse(
                count = TOTAL_CHARACTERS,
                pages = TOTAL_PAGES
            ),
            results = getCharactersResponse()
        )
    }

    private fun getCharactersResponse(): List<CharacterResponse> {
        val charactersResponse = mutableListOf<CharacterResponse>()
        repeat(PAGE_SIZE) { charactersResponse.add(MockUtils.characterResponse) }

        return charactersResponse
    }

    private fun getLocationIds(): List<Int?> {
        val originReferenceUrl = MockUtils.characterResponse.originReference.url
        val lastKnownLocationReferenceUrl = MockUtils.characterResponse.lastKnownLocationReference.url
        val originLocationId = getIdFromUrl(originReferenceUrl)
        val lastKnownLocationId = getIdFromUrl(lastKnownLocationReferenceUrl)

        return listOf(originLocationId, lastKnownLocationId)
    }

    private fun getEpisodeIds(): List<Int?> {
        val ids = mutableListOf<Int?>()
        MockUtils.characterResponse.episodesReferences.forEach { url -> ids.add(getIdFromUrl(url)) }

        return ids
    }

    private fun getIdFromUrl(url: String): Int? {
        return url.split("/").last().toIntOrNull()
    }

    private companion object {
        const val TOTAL_PAGES = 1
        const val TOTAL_CHARACTERS = PAGE_SIZE * TOTAL_PAGES
    }


}