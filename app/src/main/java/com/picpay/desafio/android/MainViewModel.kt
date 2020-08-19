package com.picpay.desafio.android

import androidx.lifecycle.MutableLiveData
import com.github.kittinunf.result.coroutines.SuspendableResult
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.usecase.UserUseCase

class MainViewModel(private val userUseCase: UserUseCase) : BaseViewModel() {
    var usersLv = MutableLiveData<List<User>>()
    var progressLv = MutableLiveData<Boolean>()
    var showErrorLv = MutableLiveData<Boolean>()

    suspend fun getUsers() {
        execute {
            progressLv.postValue(true)
            when (val usersResult = userUseCase.getUsers()) {
                is SuspendableResult.Success -> {
                    usersLv.postValue(usersResult.value)
                    progressLv.postValue(false)
                }
                is SuspendableResult.Failure -> {
                    progressLv.postValue(false)
                }
            }
        }
    }
}