package com.picpay.desafio.android.data.local.mappers

import com.picpay.desafio.android.data.local.entity.UserPO
import com.picpay.desafio.android.data.remote.entity.UserDTO
import junit.framework.TestCase
import org.junit.Test

class UserPOMapperTest {

    private val userPOMapper by lazy { UserPOMapper() }

    private val FAKE_USER_DTO = UserDTO (
        "",
        "",
        1,
        ""
    )

    private val MOCK_USER_PO = UserPO (
        "",
        "",
        1,
        ""
    )

    @Test
    fun `GIVEN users data transfer object WHEN transforming it get proper users persistent object`() {
        //Given
        //When
        val userPO = userPOMapper.transform(FAKE_USER_DTO)
        //Then
        TestCase.assertEquals(MOCK_USER_PO, userPO)
    }
}