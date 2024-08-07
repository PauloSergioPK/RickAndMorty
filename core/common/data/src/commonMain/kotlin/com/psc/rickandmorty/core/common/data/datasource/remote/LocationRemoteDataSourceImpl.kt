package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.data.mapper.toLocation
import com.psc.rickandmorty.core.common.data.service.LocationService
import com.psc.rickandmorty.core.common.domain.model.Location
import com.psc.rickandmorty.core.common.domain.util.Consts.FIRST_PAGE
import com.psc.rickandmorty.core.common.domain.util.Consts.PAGE_SIZE

internal class LocationRemoteDataSourceImpl(
    private val locationService: LocationService
) : LocationRemoteDataSource {
    override suspend fun getAllLocations(): List<Location> {
        val allLocations = mutableListOf<Location>()
        var currentPage = FIRST_PAGE
        var currentPageItemsCount = PAGE_SIZE

        while (currentPageItemsCount == PAGE_SIZE) {
            val locationsPage = getLocationsPage(currentPage)
            allLocations.addAll(locationsPage)
            currentPageItemsCount = locationsPage.size
            currentPage++
        }

        return allLocations
    }

    private suspend fun getLocationsPage(page: Int): List<Location> {
        return locationService.getPage(page).results.map { it.toLocation() }
    }

}