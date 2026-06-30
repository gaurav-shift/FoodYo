package com.example.foodyo.dataLayer.services

import com.example.foodyo.dataLayer.local.TokenManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import javax.inject.Inject
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders

class AddressApiService @Inject constructor(
    private val tokenManager: TokenManager
) {

    val client = HttpClient(CIO) {

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 15000
            connectTimeoutMillis = 15000
            socketTimeoutMillis = 15000
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTP
                host = "10.0.2.2"
                port = 5003   // Address Service
            }

            val token = runBlocking {
                tokenManager.getToken().first()
            }
            if(!token.isNullOrBlank()) {
                headers{
                    append(
                        HttpHeaders.Authorization,
                        "Bearer $token"
                    )
                }
            }
        }
    }
}