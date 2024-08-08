package com.psc.rickandmorty.core.common.domain.usecase

import com.psc.rickandmorty.core.common.domain.repository.CharactersRepository
import com.psc.rickandmorty.core.common.domain.util.Mock
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetCharactersPageUseCaseImplTest {

    private val repository by lazy { mock<CharactersRepository>() }
    private val useCase by lazy { GetCharactersPageUseCaseImpl(repository) }

    @Test
    fun `when calls useCase then calls repository`() = runTest {
        val page = (0..100).random()
        val expected = Result.success(listOf(Mock.character))

        everySuspend { repository.getPage(any()) } returns expected
        val result = useCase(page)

        assertEquals(result, expected)
        verifySuspend { repository.getPage(page) }
    }
}