package com.kitaharaa.monodropper.model.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.kitaharaa.monodropper.model.room.relation.UserWithTransaction
import com.kitaharaa.monodropper.model.room.relation.UserWithPaymentMethods
import com.kitaharaa.monodropper.model.room.tables.User

@Dao
interface UserDao {

    @Upsert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Transaction
    @Query("SELECT * FROM User INNER JOIN TransactionTable ON User.id = TransactionTable.owner_id ORDER BY TransactionTable.time ASC")
    fun getUsersWithTransactions(): List<UserWithTransaction>

    @Transaction
    @Query("SELECT * FROM User INNER JOIN TransactionTable ON User.id = TransactionTable.owner_id WHERE owner_id = :userId ORDER BY TransactionTable.time ASC")
    fun getSpecificUserTransactions(userId: Int): UserWithTransaction

    @Transaction
    @Query("SELECT * FROM User INNER JOIN PaymentMethod ON User.id = PaymentMethod.owner_id")
    fun getUsersPaymentMethods() : List<UserWithPaymentMethods>

    @Transaction
    @Query("SELECT * FROM User INNER JOIN PaymentMethod ON User.id = PaymentMethod.owner_id WHERE owner_id = :userId")
    fun getSpecificUserPaymentMethods(userId: Int) : UserWithPaymentMethods
}