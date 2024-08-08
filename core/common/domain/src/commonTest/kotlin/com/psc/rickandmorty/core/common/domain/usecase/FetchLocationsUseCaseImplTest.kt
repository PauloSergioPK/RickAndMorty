package com.psc.rickandmorty.core.common.domain.usecase

import com.psc.rickandmorty.core.common.domain.repository.LocationsRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FetchLocationsUseCaseImplTest {

    private val repository by lazy { mock<LocationsRepository>() }
    private val useCase by lazy { FetchLocationsUseCaseImpl(repository) }

    @Test
    fun `when calls useCase then calls repository`() = runTest {
        val expected = Result.success(Unit)

        everySuspend { repository.fetchLocations() } returns expected
        val result = useCase()

        assertEquals(result, expected)
        verifySuspend { repository.fetchLocations() }
    }
}