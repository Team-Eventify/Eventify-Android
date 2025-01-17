package com.example.eventify.domain.di

import com.example.eventify.BuildConfig
import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.remote.api.UsersAPI
import com.example.eventify.data.remote.utils.AccessTokenInterceptor
import com.example.eventify.data.remote.utils.NetworkServiceFactory
import com.example.eventify.data.remote.utils.TokenAuthenticator
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.data.repositories.users.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UsersModule {

    @Provides
    @Singleton
    fun provideUsersAPI(accessTokenInterceptor: AccessTokenInterceptor, tokenAuthenticator: TokenAuthenticator): UsersAPI {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(accessTokenInterceptor)
            .authenticator(tokenAuthenticator)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl("${BuildConfig.API_BASE_URL}/api/v1/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UsersAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersRepository(usersApi: UsersAPI): UsersRepository = UsersRepositoryImpl(usersApi)
}