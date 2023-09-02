package com.kitaharaa.monodropper.model.account

import kotlinx.serialization.Serializable

@Serializable
data class AccountInfo(
    val accounts: List<Account>,
    val clientId: String,
    val jars: List<Jar>,
    val name: String,
    val permissions: String,
    val webHookUrl: String
)