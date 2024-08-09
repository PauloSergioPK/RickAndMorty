package com.psc.rickandmorty.core.common.data.service

import com.psc.rickandmorty.core.common.data.model.response.CharactersPageResponse
import com.psc.rickandmorty.core.common.data.provider.HttpClientProvider
import com.psc.rickandmorty.core.common.data.util.Consts.BASE_URL
import com.psc.rickandmorty.core.common.data.util.Consts.PAGE
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class CharactersServiceImpl(
    private val httpClientProvider: HttpClientProvider
) : CharactersService {

    override suspend fun getPage(page: Int): CharactersPageResponse {
        val httpClient = httpClientProvider.get()
        val url = BASE_URL.plus(CHARACTER)

        val request = httpClient.get(url) {
            parameter(PAGE, page)
        }

        return request.body<CharactersPageResponse>()
    }

    private companion object {
        const val CHARACTER = "character/"
    }
}