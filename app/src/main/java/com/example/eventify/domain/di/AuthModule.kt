package com.example.eventify.domain.di

import android.app.Activity
import android.content.Context
import com.example.eventify.BuildConfig
import com.example.eventify.data.repositories.tokens.PreferencesTokenManagerImpl
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.data.remote.utils.AccessTokenInterceptor
import com.example.eventify.data.remote.utils.NetworkServiceFactory
import com.example.eventify.data.remote.utils.TokenAuthenticator
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.data.repositories.auth.AuthUserRepositoryImpl
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.domain.SessionManager
import com.example.eventify.domain.SessionManagerImpl
import com.example.eventify.domain.SessionManagerRequestsImpl
import com.example.eventify.domain.services.AccountCredentialManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
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
    fun provideAuthAPI(): AuthAPI = NetworkServiceFactory.getApi("${BuildConfig.API_BASE_URL}/api/v1/auth/", AuthAPI::class.java)

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

    @DecodeSessionManager
    @Provides
    @Singleton
    fun provideDecodeSessionManager(tokenManager: TokenManager): SessionManager = SessionManagerImpl(tokenManager = tokenManager)

    @RequestsSessionManager
    @Provides
    @Singleton
    fun provideRequestsSessionManager(usersRepository: UsersRepository, tokenManager: TokenManager): SessionManager = SessionManagerRequestsImpl(usersRepository = usersRepository, tokenManager = tokenManager)

}