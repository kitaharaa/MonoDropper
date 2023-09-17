package com.kitaharaa.monodropper.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kitaharaa.monodropper.model.room.daos.PaymentMethodDao
import com.kitaharaa.monodropper.model.room.daos.TransactionDao
import com.kitaharaa.monodropper.model.room.daos.UserDao
import com.kitaharaa.monodropper.model.room.tables.PaymentMethod
import com.kitaharaa.monodropper.model.room.tables.Transaction
import com.kitaharaa.monodropper.model.room.tables.User

@Database(
    entities = [User::class, Transaction::class, PaymentMethod::class],
    version = 1
)
abstract class MonoDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun transactionDao(): TransactionDao
    abstract fun paymentMethodDao(): PaymentMethodDao
}