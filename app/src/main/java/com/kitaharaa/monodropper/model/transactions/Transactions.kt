package com.kitaharaa.monodropper.model.transactions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Transactions(
    val amount: Int,
    val balance: Int,
    val cashbackAmount: Int,
    val commissionRate: Int,
    val currencyCode: Int,
    val description: String,
    val hold: Boolean,
    val id: String,
    val mcc: Int,
    val operationAmount: Int,
    val originalMcc: Int,
    @SerialName("receiptId")
    val receiptId: String,
    val time: Int
)