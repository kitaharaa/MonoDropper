package com.kitaharaa.monodropper.model.room.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.kitaharaa.monodropper.model.room.tables.Transaction
import com.kitaharaa.monodropper.model.room.tables.User

data class UserWithTransaction(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "owner_id"
    )
    val transactions: List<Transaction>
)
