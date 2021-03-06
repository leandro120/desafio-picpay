package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.remote.entity.UserDTO
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<UserDTO>>
}