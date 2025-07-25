package com.psc.rickandmorty.core.common.data.service

import com.psc.rickandmorty.core.common.data.model.response.EpisodesPageResponse
import com.psc.rickandmorty.core.common.data.provider.HttpClientProvider
import com.psc.rickandmorty.core.common.data.util.Consts.BASE_URL
import com.psc.rickandmorty.core.common.data.util.Consts.PAGE
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class EpisodeServiceImpl(
    private val httpClientProvider: HttpClientProvider
) : EpisodeService {

    override suspend fun getPage(page: Int): EpisodesPageResponse {
        val httpClient = httpClientProvider.get()
        val url = BASE_URL.plus(EPISODE)

        val request = httpClient.get(url) {
            parameter(PAGE, page)
        }


        return request.body<EpisodesPageResponse>()
    }

    private companion object {
        const val EPISODE = "episode/"
    }
}