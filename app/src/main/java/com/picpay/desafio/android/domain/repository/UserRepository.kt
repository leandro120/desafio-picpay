package com.picpay.desafio.android.domain.repository

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.picpay.desafio.android.domain.entity.User

interface UserRepository {
    suspend fun getUsers(): SuspendableResult<List<User>, Exception>
    suspend fun getLocalUsers(): List<User>
}