package com.kitaharaa.monodropper.model.api.account

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val balance: Int,
    val cashbackType: String,
    val creditLimit: Int,
    val currencyCode: Int,
    val iban: String,
    val id: String,
    val maskedPan: List<String>,
    val sendId: String,
    val type: String
)