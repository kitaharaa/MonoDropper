package com.kitaharaa.monodropper.model.transactions

import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val amount: Int,
    val balance: Int,
    val cashbackAmount: Int,
    val comment: String? = null,
    val commissionRate: Int,
    val counterEdrpou: String? = null,
    val counterIban: String? = null,
    val counterName: String? = null,
    val currencyCode: Int,
    val description: String,
    val hold: Boolean,
    val id: String,
    val invoiceId: String? = null,
    val mcc: Int,
    val operationAmount: Int,
    val originalMcc: Int,
    val receiptId: String? = null,
    val time: Int
)