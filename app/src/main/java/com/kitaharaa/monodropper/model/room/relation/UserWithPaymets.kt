package com.kitaharaa.monodropper.model.room.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.kitaharaa.monodropper.model.room.tables.PaymentMethod
import com.kitaharaa.monodropper.model.room.tables.User

data class UserWithPaymentMethods(
    @Embedded val user: User,

    @Relation(
        parentColumn = "id",
        entityColumn = "owner_id"
    ) val paymentMethods: List<PaymentMethod>
)
