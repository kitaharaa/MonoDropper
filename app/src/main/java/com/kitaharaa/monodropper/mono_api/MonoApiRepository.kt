package com.kitaharaa.monodropper.mono_api

import com.kitaharaa.monodropper.model.api.account.AccountInfo
import com.kitaharaa.monodropper.model.api.transactions.Transaction

interface MonoApiRepository {
    suspend fun getUserInfo(xToken: String?): AccountInfo?
    suspend fun getAccountTransaction(
        xToken: String?,
        accountId: String?,
        timeFrom: String,
        timeTo: String? = null
    ):  List<Transaction>?
}