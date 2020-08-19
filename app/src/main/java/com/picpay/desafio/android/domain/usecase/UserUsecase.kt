package com.picpay.desafio.android.domain.usecase

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.picpay.desafio.android.domain.entity.User
import java.lang.Exception

interface UserUseCase {
    suspend fun getUsers() : SuspendableResult<List<User>, Exception>
}