package com.example.foodyo.dataLayer.services

import com.example.foodyo.dataLayer.local.TokenManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class CartApiService(
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
            requestTimeoutMillis = 30000
            connectTimeoutMillis = 30000
            socketTimeoutMillis = 30000
        }

        defaultRequest {

            url {
                protocol = URLProtocol.HTTP
                host = "10.0.2.2"
                port = 5005
            }

            val token = runBlocking {
                tokenManager.getToken().first()
            }

            token?.let {
                header(
                    HttpHeaders.Authorization,
                    "Bearer $it"
                )
            }
        }
    }
}