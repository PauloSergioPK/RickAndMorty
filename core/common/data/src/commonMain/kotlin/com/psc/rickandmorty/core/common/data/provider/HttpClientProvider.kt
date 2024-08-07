package com.psc.rickandmorty.core.common.data.provider

import io.ktor.client.HttpClient

interface HttpClientProvider {
    suspend fun get(): HttpClient
}