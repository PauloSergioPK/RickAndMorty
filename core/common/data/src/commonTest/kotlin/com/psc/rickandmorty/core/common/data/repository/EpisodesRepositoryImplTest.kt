package com.psc.rickandmorty.core.common.data.repository

import com.psc.rickandmorty.core.common.data.datasource.local.EpisodeLocalDataSource
import com.psc.rickandmorty.core.common.data.datasource.remote.EpisodeRemoteDataSource
import com.psc.rickandmorty.core.common.data.util.Mock
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

class EpisodesRepositoryImplTest {

    private val localDataSource by lazy { mock<EpisodeLocalDataSource>() }
    private val remoteDataSource by lazy { mock<EpisodeRemoteDataSource>() }
    private val repository by lazy {
        EpisodesRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

    @Test
    fun `when calls fetchEpisodes and remoteDataSource returns episodes then add them on localDataSource and return ResultSuccess`() {
        runTest {
            val episodes = listOf(Mock.episode)
            val expected = Result.success(Unit)

            everySuspend { remoteDataSource.getAllEpisodes() } returns episodes
            everySuspend { localDataSource.addEpisodes(any()) } returns Unit
            val result = repository.fetchEpisodes()

            assertEquals(result, expected)
            verifySuspend {
                remoteDataSource.getAllEpisodes()
                localDataSource.addEpisodes(episodes)
            }
        }
    }

    @Test
    fun `when calls fetchEpisodes and remoteDataSource fails then return ResultFailure`() {
        runTest {
            val exception = Exception(REMOTE_DATA_SOURCE_EXCEPTION_MESSAGE)
            val expected = Result.failure<Unit>(exception)

            everySuspend { remoteDataSource.getAllEpisodes() } throws exception
            val result = repository.fetchEpisodes()

            assertEquals(result, expected)
            verifySuspend { remoteDataSource.getAllEpisodes() }
            verifySuspend(mode = VerifyMode.not) { localDataSource.addEpisodes(any()) }
        }
    }

    private companion object {
        const val REMOTE_DATA_SOURCE_EXCEPTION_MESSAGE = "exception on getAllEpisodes"
    }
}