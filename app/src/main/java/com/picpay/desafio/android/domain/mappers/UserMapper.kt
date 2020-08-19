package com.picpay.desafio.android.domain.mappers

import com.picpay.desafio.android.BaseMapper
import com.picpay.desafio.android.data.local.entity.UserPO
import com.picpay.desafio.android.domain.entity.User

class UserMapper : BaseMapper<UserPO, User>() {
    override fun transform(entity: UserPO): User {
        return User(
            img = entity.img, name = entity.name, id = entity.id, username = entity.username
        )
    }
}