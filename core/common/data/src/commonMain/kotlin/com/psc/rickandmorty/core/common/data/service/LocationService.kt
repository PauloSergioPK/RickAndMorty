package com.psc.rickandmorty.core.common.data.service

import com.psc.rickandmorty.core.common.data.model.response.LocationsPageResponse

interface LocationService {
    suspend fun getPage(page: Int): LocationsPageResponse
}