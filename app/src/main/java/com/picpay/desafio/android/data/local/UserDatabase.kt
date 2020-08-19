package com.picpay.desafio.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.entity.UserPO

@Database(entities = [UserPO::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDAO() : UserDAO
}