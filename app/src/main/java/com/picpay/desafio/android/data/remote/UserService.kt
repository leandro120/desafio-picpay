package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.remote.entity.UserDTO
import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<UserDTO>
}