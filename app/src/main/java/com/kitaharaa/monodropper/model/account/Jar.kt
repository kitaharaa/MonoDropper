package com.kitaharaa.monodropper.model.account

import kotlinx.serialization.Serializable

@Serializable
data class Jar(
    val balance: Int,
    val currencyCode: Int,
    val description: String,
    val goal: Int,
    val id: String,
    val sendId: String,
    val title: String
)