package com.psc.rickandmorty.core.common.data.repository

import com.psc.rickandmorty.core.common.data.datasource.local.LocationLocalDataSource
import com.psc.rickandmorty.core.common.data.datasource.remote.LocationRemoteDataSource
import com.psc.rickandmorty.core.common.data.util.MockUtils
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LocationsRepositoryImplTest {

    private val localDataSource by lazy { mock<LocationLocalDataSource>() }
    private val remoteDataSource by lazy { mock<LocationRemoteDataSource>() }
    private val repository by lazy {
        LocationsRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

    @Test
    fun `when calls fetchLocations and remoteDataSource returns locations then add them on localDataSource and return ResultSuccess`() {
        runTest {
            val locations = listOf(MockUtils.location)
            val expected = Result.success(Unit)

            everySuspend { remoteDataSource.getAllLocations() } returns locations
            everySuspend { localDataSource.addLocations(any()) } returns Unit
            val result = repository.fetchLocations()

            assertEquals(result, expected)
            verifySuspend {
                remoteDataSource.getAllLocations()
                localDataSource.addLocations(locations)
            }
        }
    }

    @Test
    fun `when calls fetchLocations and remoteDataSource fails then return ResultFailure`() {
        runTest {
            val exception = Exception(REMOTE_DATA_SOURCE_EXCEPTION_MESSAGE)
            val expected = Result.failure<Unit>(exception)

            everySuspend { remoteDataSource.getAllLocations() } throws exception
            val result = repository.fetchLocations()

            assertEquals(result, expected)
            verifySuspend { remoteDataSource.getAllLocations() }
            verifySuspend(mode = VerifyMode.not) { localDataSource.addLocations(any()) }
        }
    }

    private companion object {
        const val REMOTE_DATA_SOURCE_EXCEPTION_MESSAGE = "exception on getAllLocations"
    }
}