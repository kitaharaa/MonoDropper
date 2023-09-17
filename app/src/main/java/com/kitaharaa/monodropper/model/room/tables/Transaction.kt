package com.kitaharaa.monodropper.model.room.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TransactionTable")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("owner_id") val ownerId: Int,
    @ColumnInfo("operation_amount") val operationAmount: Int,
    @ColumnInfo("time") val time: Int,
    val balance: Int,
)