package com.picpay.desafio.android.data

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.picpay.desafio.android.data.local.UserDAO
import com.picpay.desafio.android.data.local.entity.UserPO
import com.picpay.desafio.android.data.local.mappers.UserPOMapper
import com.picpay.desafio.android.data.remote.entity.UserDTO
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.mappers.UserMapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest : BaseWebTest() {

    @MockK
    private lateinit var userDAO: UserDAO

    @MockK
    private lateinit var userPOMapper: UserPOMapper

    @MockK
    private lateinit var userMapper: UserMapper

    private val userRepositoryImpl by lazy {
        UserRepositoryImpl(
            userDAO,
            retrofit.create(UserService::class.java),
            userPOMapper,
            userMapper
        )
    }

    @Before
    fun onSetup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        startMockWebServer()
    }

    @After
    fun tearDown() {
        closeMockWebServer()
    }

    @Test
    fun `GIVEN a valid response WHEN getting users THEN query retrofit client and return success`() {
        //Given
        mockServer.enqueue(
            MockResponse()
                .setBody(getJson(USERS_RESPONSE_JSON))
        )

        every {
            userDAO.getUsers()
        } returns MOCK_CART_ITEM_PO_LIST

        every {
            userPOMapper.transform(any<UserDTO>())
        } returns MOCK_USER_PO

        every {
            userMapper.transform(any<UserPO>())
        } returns MOCK_USER

        // When
        val result = runBlocking {
            userRepositoryImpl.getUsers()
        }

        TestCase.assertTrue(result is SuspendableResult.Success<*, *>)
    }

    companion object {
        private const val USERS_RESPONSE_JSON = "UsersResponse.json"

        private val MOCK_USER_PO = UserPO(
            "",
            "",
            1,
            ""
        )

        private val MOCK_USER = User(
            "",
            "",
            1,
            ""
        )

        private val MOCK_CART_ITEM_PO_LIST = listOf<UserPO>(MOCK_USER_PO)
    }
}