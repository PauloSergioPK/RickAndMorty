package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.data.mapper.toLocation
import com.psc.rickandmorty.core.common.data.service.LocationService
import com.psc.rickandmorty.core.common.domain.model.Location
import com.psc.rickandmorty.core.common.domain.util.Consts
import com.psc.rickandmorty.core.common.domain.util.Consts.FIRST_PAGE

internal class LocationRemoteDataSourceImpl(
    private val locationService: LocationService
) : LocationRemoteDataSource {
    override suspend fun getAllLocations(): List<Location> {
        val allLocations = mutableListOf<Location>()

        val firstPageResponse = locationService.getPage(FIRST_PAGE)
        val totalPages = firstPageResponse.infoResponse.pages
        val firstPageLocations = firstPageResponse.results.map { it.toLocation() }
        allLocations.addAll(firstPageLocations)

        for (page in Consts.SECOND_PAGE..totalPages) {
            val episodesPage = getLocationsPage(page)
            allLocations.addAll(episodesPage)
        }

        return allLocations
    }

    private suspend fun getLocationsPage(page: Int): List<Location> {
        return locationService.getPage(page).results.map { it.toLocation() }
    }

}