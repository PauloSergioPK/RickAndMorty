package com.psc.rickandmorty.core.common.domain.usecase

import com.psc.rickandmorty.core.common.domain.repository.EpisodesRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FetchEpisodesUseCaseImplTest {

    private val repository by lazy { mock<EpisodesRepository>() }
    private val useCase by lazy { FetchEpisodesUseCaseImpl(repository) }

    @Test
    fun `when calls useCase then calls repository`() = runTest {
        val expected = Result.success(Unit)

        everySuspend { repository.fetchEpisodes() } returns expected
        val result = useCase()

        assertEquals(result, expected)
        verifySuspend { repository.fetchEpisodes() }
    }
}