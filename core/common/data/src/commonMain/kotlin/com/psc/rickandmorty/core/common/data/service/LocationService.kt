package com.psc.rickandmorty.core.common.data.service

import com.psc.rickandmorty.core.common.data.model.LocationResponse

interface LocationService {
    suspend fun getLocation(location: Int): LocationResponse
}