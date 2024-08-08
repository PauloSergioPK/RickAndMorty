package com.psc.rickandmorty.core.common.data.repository

import com.psc.rickandmorty.core.common.data.datasource.remote.CharactersRemoteDataSource
import com.psc.rickandmorty.core.common.data.util.Mock
import com.psc.rickandmorty.core.common.domain.model.Character
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CharactersRepositoryImplTest {

    private val remoteDataSource by lazy { mock<CharactersRemoteDataSource>() }
    private val repository by lazy { CharactersRepositoryImpl(remoteDataSource) }

    @Test
    fun `when calls getPage and remoteDataSource returns characters then return ResultSuccess with them`() {
        runTest {
            val page = (0..100).random()
            val characters = listOf(Mock.character)
            val expected = Result.success(characters)

            everySuspend { remoteDataSource.getPage(any()) } returns characters
            val result = repository.getPage(page)

            assertEquals(result, expected)
            verifySuspend { remoteDataSource.getPage(page) }
        }
    }

    @Test
    fun `when calls getPage and remoteDataSource fails then return ResultFailure`() {
        runTest {
            val page = (0..100).random()
            val exception = Exception(REMOTE_DATA_SOURCE_EXCEPTION_MESSAGE)
            val expected = Result.failure<List<Character>>(exception)

            everySuspend { remoteDataSource.getPage(any()) } throws exception
            val result = repository.getPage(page)

            assertEquals(result, expected)
            verifySuspend { remoteDataSource.getPage(page) }
        }
    }

    private companion object {
        const val REMOTE_DATA_SOURCE_EXCEPTION_MESSAGE = "exception on getPage"
    }
}