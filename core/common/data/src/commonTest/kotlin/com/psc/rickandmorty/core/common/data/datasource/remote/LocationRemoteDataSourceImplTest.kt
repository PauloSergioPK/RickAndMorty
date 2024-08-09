package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.data.model.response.LocationResponse
import com.psc.rickandmorty.core.common.data.model.response.LocationsPageResponse
import com.psc.rickandmorty.core.common.data.model.response.PageInfoResponse
import com.psc.rickandmorty.core.common.data.service.LocationService
import com.psc.rickandmorty.core.common.data.util.Mock
import com.psc.rickandmorty.core.common.domain.model.Location
import com.psc.rickandmorty.core.common.domain.util.Consts.PAGE_SIZE
import dev.mokkery.answering.calls
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LocationRemoteDataSourceImplTest {

    private val service by lazy { mock<LocationService>() }
    private val datasource by lazy { LocationRemoteDataSourceImpl(service) }

    @Test
    fun `when calls getAllEpisodes then request all pages to service`() = runTest {
        val expected = mutableListOf<Location>()
        repeat(TOTAL_LOCATIONS) { expected.add(Mock.location) }
        val pages = mutableListOf<LocationsPageResponse>()
        repeat(TOTAL_PAGES) {
            pages.add(
                LocationsPageResponse(
                    infoResponse = PageInfoResponse(
                        count = PAGE_SIZE * TOTAL_PAGES,
                        pages = TOTAL_PAGES
                    ),
                    results = getLocationsResponse()
                )
            )
        }

        everySuspend { service.getPage(any()) } calls { (page: Int) -> pages[page.dec()] }
        val result = datasource.getAllLocations()

        assertEquals(result, expected)
        repeat(TOTAL_PAGES) { page ->
            verifySuspend {
                service.getPage(page.inc())
            }
        }
    }

    private fun getLocationsResponse(): List<LocationResponse> {
        val episodesResponse = mutableListOf<LocationResponse>()
        repeat(PAGE_SIZE) { episodesResponse.add(Mock.locationResponse) }

        return episodesResponse
    }

    private companion object {
        const val TOTAL_PAGES = 3
        const val TOTAL_LOCATIONS = 60
    }

}