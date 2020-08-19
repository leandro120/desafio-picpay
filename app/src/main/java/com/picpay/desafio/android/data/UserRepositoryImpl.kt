package com.picpay.desafio.android.data

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.picpay.desafio.android.data.local.UserDAO
import com.picpay.desafio.android.data.local.mappers.UserPOMapper
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.mappers.UserMapper
import com.picpay.desafio.android.domain.repository.UserRepository

class UserRepositoryImpl(
    private var userDAO: UserDAO,
    private var userService: UserService,
    private var userPOMapper: UserPOMapper,
    private var userMapper: UserMapper
) : UserRepository {
    override suspend fun getUsers(): SuspendableResult<List<User>, Exception> {

        return SuspendableResult.of {
            val usersPO = userService.getUsers()
                .map(userPOMapper::transform)

            usersPO.forEach(userDAO::insert)

            usersPO.map(userMapper::transform)
        }
    }

    override suspend fun getLocalUsers(): List<User> = userDAO.getUsers().map(userMapper::transform)
}
