package com.picpay.desafio.android

import androidx.multidex.BuildConfig
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.picpay.desafio.android.data.UserRepositoryImpl
import com.picpay.desafio.android.data.UserService
import com.picpay.desafio.android.data.local.UserDatabase
import com.picpay.desafio.android.data.local.mappers.UserPOMapper
import com.picpay.desafio.android.domain.mappers.UserMapper
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.usecase.UserUseCase
import com.picpay.desafio.android.domain.usecase.UserUseCaseImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_API_URL =
    "http://careers.picpay.com/tests/mobdev/"

val appModule = module {

    viewModel {
        MainViewModel(
            get<UserUseCase>()
        )
    }

    factory { UserUseCaseImpl(get<UserRepository>()) as UserUseCase }

    factory {
        UserRepositoryImpl(
            get<UserDatabase>().userDAO(),
            get<UserService>(),
            UserPOMapper(),
            UserMapper()
        ) as UserRepository
    }

    single {
        Room.databaseBuilder(androidApplication(), UserDatabase::class.java, "user-db").build()
    }

    single {
        get<Retrofit> { parametersOf(BASE_API_URL) }.create(UserService::class.java)
    }

    factory { (retrofitUrl: String) ->
        val retrofitClient = Retrofit.Builder()

        retrofitClient
            .baseUrl(retrofitUrl)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory {
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }
}