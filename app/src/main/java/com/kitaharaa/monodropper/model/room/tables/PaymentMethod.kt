package com.kitaharaa.monodropper.model.room.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PaymentMethod")
data class PaymentMethod(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("owner_id") val ownerId: Int,
    @ColumnInfo("payment_id") val paymentId: String,
    val balance: Int,
    @ColumnInfo("masked_pan") val maskedPan: String
)