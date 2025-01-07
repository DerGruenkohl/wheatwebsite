package com.dergruenkohl.wheatwebsite

import com.dergruenkohl.wheatwebsite.Config.key
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


object Api {
    val client = HttpClient(CIO) {
        expectSuccess = true
        install(Logging)
        install(HttpRequestRetry) {
            retryOnServerErrors(3)
            retryOnException(3, true)
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}