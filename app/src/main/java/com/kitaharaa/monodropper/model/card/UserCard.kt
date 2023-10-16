package com.kitaharaa.monodropper.model.card

data class UserCard(
    val userName: String,
    val cartNumber: String,
    val cardType: CardType,
    val monthReplenishment: Int,
    val monthDebit: Int
)