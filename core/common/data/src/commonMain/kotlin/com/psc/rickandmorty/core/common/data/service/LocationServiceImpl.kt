package com.psc.rickandmorty.core.common.data.service

import com.psc.rickandmorty.core.common.data.model.LocationResponse
import com.psc.rickandmorty.core.common.data.provider.HttpClientProvider
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class LocationServiceImpl(
    private val httpClientProvider: HttpClientProvider
) : LocationService {

    override suspend fun getLocation(location: Int): LocationResponse {
        val httpClient = httpClientProvider.get()
        val url = BASE_URL.plus(LOCATION).plus(location)

        val request = httpClient.get(url)

        return request.body<LocationResponse>()
    }

    private companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
        const val LOCATION = "location/"
    }
}