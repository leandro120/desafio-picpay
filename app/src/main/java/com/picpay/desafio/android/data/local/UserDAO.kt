package com.picpay.desafio.android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.local.entity.UserPO

@Dao
interface UserDAO {

    @Query("SELECT * FROM user")
    fun getUsers(): List<UserPO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userPO: UserPO)
}