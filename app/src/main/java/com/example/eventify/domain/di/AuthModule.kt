package com.example.eventify.domain.di

import android.content.Context
import com.example.eventify.data.remote.utils.PreferencesTokenManagerImpl
import com.example.eventify.data.remote.utils.TokenManager
import com.example.eventify.data.remote.utils.AccessTokenInterceptor
import com.example.eventify.data.remote.utils.NetworkServiceFactory
import com.example.eventify.data.remote.utils.TokenAuthenticator
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.repositories.AuthUserRepository
import com.example.eventify.data.repositories.AuthUserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Interceptor
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthAPI(): AuthAPI = NetworkServiceFactory.getApi("http://188.225.82.113:8090/api/v1/auth/", AuthAPI::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthAPI): AuthUserRepository = AuthUserRepositoryImpl(authApi)

    @Provides
    @Singleton
    fun provideAccessTokenInterceptor(tokenManager: TokenManager): Interceptor = AccessTokenInterceptor(tokenManager)

    @Provides
    @Singleton
    fun provideTokenAuthenticator(tokenManager: TokenManager, authRepository: AuthUserRepository): Authenticator = TokenAuthenticator(tokenManager = tokenManager, authRepository = authRepository)

    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager = PreferencesTokenManagerImpl(context)

}