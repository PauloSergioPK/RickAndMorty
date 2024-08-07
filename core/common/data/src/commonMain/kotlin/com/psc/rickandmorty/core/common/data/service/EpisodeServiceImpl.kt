package com.psc.rickandmorty.core.common.data.service

import com.psc.rickandmorty.core.common.data.model.EpisodeResponse
import com.psc.rickandmorty.core.common.data.provider.HttpClientProvider
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class EpisodeServiceImpl(
    private val httpClientProvider: HttpClientProvider
) : EpisodeService {

    override suspend fun getEpisode(episode: Int): EpisodeResponse {
        val httpClient = httpClientProvider.get()
        val url = BASE_URL.plus(EPISODE).plus(episode)

        val request = httpClient.get(url)

        return request.body<EpisodeResponse>()
    }

    private companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
        const val EPISODE = "episode/"
    }
}