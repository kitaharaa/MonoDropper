package com.kitaharaa.monodropper.di.modules

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.PortUnreachableException
import java.net.ProtocolException
import java.net.SocketException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class KtorClientModule {
    @Provides
    fun provideKtorClient() = HttpClient(Android) {
        // Logging
        install(Logging) {

            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Log.e("TAG", "log: $message")
                }

            }
        }

        // JSON
        install(ContentNegotiation) {
            json()
        }

        // Timeout
        install(HttpTimeout) {
            val time = 150000L
            requestTimeoutMillis = time
            connectTimeoutMillis = time
            socketTimeoutMillis = time

        }

        install(HttpRequestRetry) {
            maxRetries = DEFAULT_MAX_RETRIES
            retryOnException(DEFAULT_MAX_RETRIES)
            retryOnServerErrors(DEFAULT_MAX_RETRIES)
            retryIf { _, response ->
                response.status.isSuccess().not()
            }
            retryOnExceptionIf { _, cause ->
                (cause is UnknownHostException) or
                        (cause is java.net.SocketTimeoutException) or
                        (cause is ConnectException) or
                        (cause is NoRouteToHostException) or
                        (cause is PortUnreachableException) or
                        (cause is ProtocolException) or
                        (cause is SocketException) or
                        (cause is Exception)
            }

            delayMillis { retry ->
                retry * TimeUnit.SECONDS.toMillis(DEFAULT_DELAY_SECONDS)
            }
        }

        /*   // Apply to all requests
           defaultRequest {
               contentType(ContentType.Application.Json)
               accept(ContentType.Application.Json)
           }
   */
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}


val DEFAULT_DELAY_SECONDS = 1L
val DEFAULT_MAX_RETRIES = 1