package com.picpay.desafio.android.data.local.mappers

import com.picpay.desafio.android.BaseMapper
import com.picpay.desafio.android.data.remote.entity.UserDTO
import com.picpay.desafio.android.data.local.entity.UserPO

class UserPOMapper : BaseMapper<UserDTO, UserPO>() {
    override fun transform(entity: UserDTO): UserPO {
        return UserPO(
            img = entity.img, name = entity.name, id = entity.id, username = entity.username
        )
    }
}