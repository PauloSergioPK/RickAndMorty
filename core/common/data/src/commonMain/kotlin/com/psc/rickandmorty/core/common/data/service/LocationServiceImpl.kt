package com.psc.rickandmorty.core.common.data.service

import com.psc.rickandmorty.core.common.data.model.response.LocationsPageResponse
import com.psc.rickandmorty.core.common.data.provider.HttpClientProvider
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class LocationServiceImpl(
    private val httpClientProvider: HttpClientProvider
) : LocationService {

    override suspend fun getPage(page: Int): LocationsPageResponse {
        val httpClient = httpClientProvider.get()
        val url = BASE_URL.plus(LOCATION)

        val request = httpClient.get(url) {
            parameter(PAGE, page)
        }

        return request.body<LocationsPageResponse>()
    }

    private companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
        const val LOCATION = "location/"
        const val PAGE = "page"
    }
}