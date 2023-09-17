package com.kitaharaa.monodropper.model.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import com.kitaharaa.monodropper.model.room.tables.Transaction

@Dao
interface TransactionDao {
    @Upsert
    fun insert(transaction: Transaction)

    @Delete
    fun delete(transaction: Transaction)
}