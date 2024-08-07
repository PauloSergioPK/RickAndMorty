package com.psc.rickandmorty.core.common.domain.di

import com.psc.rickandmorty.core.common.domain.usecase.GetCharactersPageUseCase
import com.psc.rickandmorty.core.common.domain.usecase.GetCharactersPageUseCaseImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonDomainModule = module {
    factoryOf(::GetCharactersPageUseCaseImpl) bind GetCharactersPageUseCase::class
}