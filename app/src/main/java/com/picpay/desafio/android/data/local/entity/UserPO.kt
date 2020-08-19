package com.picpay.desafio.android.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserPO(
    val img: String, val name: String, @PrimaryKey val id: Int, val username: String
)