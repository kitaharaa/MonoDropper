package com.kitaharaa.monodropper.model.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import com.kitaharaa.monodropper.model.room.tables.PaymentMethod

@Dao
interface PaymentMethodDao {
    @Upsert
    fun insert(transaction: PaymentMethod)

    @Delete
    fun delete(transaction: PaymentMethod)
}