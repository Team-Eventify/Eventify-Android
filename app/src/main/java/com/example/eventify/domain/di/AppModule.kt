package com.example.eventify.domain.di

import com.example.eventify.data.remote.NetworkServiceFactory
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.repositories.AuthUserRepository
import com.example.eventify.data.repositories.AuthUserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthAPI(): AuthAPI = NetworkServiceFactory.getApi("http://188.225.82.113:8090/api/v1/auth/", AuthAPI::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthAPI): AuthUserRepository {
        return AuthUserRepositoryImpl(authApi)
    }
}