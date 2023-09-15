package com.kitaharaa.monodropper.mono_api

import android.util.Log
import com.kitaharaa.monodropper.model.account.AccountInfo
import com.kitaharaa.monodropper.model.transactions.Transaction
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.path
import javax.inject.Inject

// todo extract build function
class MonoApiImpl @Inject constructor(
    private val client: HttpClient
) : MonoApiRepository {
    override suspend fun getUserInfo(xToken: String?): AccountInfo? =
        try {
            client.buildGetRequest(
                path = "personal/client-info",
                key = xToken
            ).body()
        } catch (e: Exception) {
            Log.e(TAG, "getUserInfo: ", e)
            null
        }

    override suspend fun getAccountTransaction(
        xToken: String?,
        accountId: String?,
        timeFrom: String,
        timeTo: String?
    ): List<Transaction>? = try {
        val fullPath =
            "personal/statement/$accountId/$timeFrom${if (timeTo != null) "/$timeTo" else ""}"

        Log.e("MonoApiImpl", "getAccountTransaction: fullPath = $fullPath")
        client.request {

            val monoHost = "api.monobank.ua"
            url {
                protocol = URLProtocol.HTTPS
                host = monoHost
                path(fullPath)
            }
            val keyHeader = "X-Token"

            method = HttpMethod.Get
            header(keyHeader, xToken)
        }.body()
    } catch (e: Exception) {
        Log.e(TAG, "getAccountTransaction: ", e)
        null
    }

    private suspend fun HttpClient.buildGetRequest(path: String, key: String?) = request {
        url {
            setBasics()
            path(path)
        }

        setQueryPreferences(key = key)
    }

    private fun HttpRequestBuilder.setQueryPreferences(key: String?) {
        val keyHeader = "X-Token"

        method = HttpMethod.Get
        header(keyHeader, key)
    }

    private fun URLBuilder.setBasics() {
        val monoHost = "api.monobank.ua"

        protocol = URLProtocol.HTTPS
        host = monoHost
    }

    companion object {
        const val TAG = "MonoApiImpl"
    }
}