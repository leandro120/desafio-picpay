package com.picpay.desafio.android.domain.usecase

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.repository.UserRepository

class UserUseCaseImpl(private val userRepository: UserRepository) : UserUseCase {
    override suspend fun getUsers(): SuspendableResult<List<User>, Exception> {
        return when (val usersRepo = userRepository.getUsers()) {
            is SuspendableResult.Success -> {
                usersRepo
            }
            is SuspendableResult.Failure -> {
                SuspendableResult.of {
                    userRepository.getLocalUsers()
                }
            }
        }
    }
}
