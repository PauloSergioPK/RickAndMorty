package com.psc.rickandmorty.core.common.data.di

import com.psc.rickandmorty.core.common.data.datasource.remote.CharactersRemoteDataSource
import com.psc.rickandmorty.core.common.data.datasource.remote.CharactersRemoteDataSourceImpl
import com.psc.rickandmorty.core.common.data.provider.HttpClientProvider
import com.psc.rickandmorty.core.common.data.provider.HttpClientProviderImpl
import com.psc.rickandmorty.core.common.data.repository.CharactersRepositoryImpl
import com.psc.rickandmorty.core.common.data.service.CharactersService
import com.psc.rickandmorty.core.common.data.service.CharactersServiceImpl
import com.psc.rickandmorty.core.common.data.service.EpisodeService
import com.psc.rickandmorty.core.common.data.service.EpisodeServiceImpl
import com.psc.rickandmorty.core.common.data.service.LocationService
import com.psc.rickandmorty.core.common.data.service.LocationServiceImpl
import com.psc.rickandmorty.core.common.domain.repository.CharactersRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonDataModule = module {
    factoryOf(::CharactersRepositoryImpl) bind CharactersRepository::class
    factoryOf(::CharactersServiceImpl) bind CharactersService::class
    factoryOf(::CharactersRemoteDataSourceImpl) bind CharactersRemoteDataSource::class
    factoryOf(::HttpClientProviderImpl) bind HttpClientProvider::class
    factoryOf(::LocationServiceImpl) bind LocationService::class
    factoryOf(::EpisodeServiceImpl) bind EpisodeService::class
}