package com.psc.rickandmorty.core.common.data.service

import com.psc.rickandmorty.core.common.data.model.response.CharacterResponse
import com.psc.rickandmorty.core.common.data.model.response.CharactersPageResponse
import com.psc.rickandmorty.core.common.data.model.response.PageInfoResponse
import com.psc.rickandmorty.core.common.data.provider.HttpClientProvider
import com.psc.rickandmorty.core.common.data.util.MockUtils
import com.psc.rickandmorty.core.common.domain.util.Consts.FIRST_PAGE
import com.psc.rickandmorty.core.common.domain.util.Consts.PAGE_SIZE
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class CharactersServiceImplTest {

    private val httpClientProvider by lazy { mock<HttpClientProvider>() }
    private val service by lazy { CharactersServiceImpl(httpClientProvider) }

    @Test
    fun `when call getPage returns a CharactersPageResponse`() {
        runTest {
            val page = FIRST_PAGE

            everySuspend { httpClientProvider.get() } returns getOkResponse()
            val expected = getPageResponse()
            val result = service.getPage(page)

            assertEquals(result, expected)
            verifySuspend { httpClientProvider.get() }
        }
    }

    private fun getOkResponse(): HttpClient {
        val responseJson = Json.encodeToString(getPageResponse())
        return MockUtils.getHttpClient(
            MockEngine {
                respond(
                    content = ByteReadChannel(responseJson),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        )
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

    private companion object {
        const val TOTAL_PAGES = 1
        const val TOTAL_CHARACTERS = PAGE_SIZE * TOTAL_PAGES
    }
}