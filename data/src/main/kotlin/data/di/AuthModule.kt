package data.di

import com.example.eventify.data.BuildConfig
import core.common.secure.tokens.TokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.remote.api.AuthAPI
import data.remote.utils.AccessTokenInterceptor
import data.remote.utils.NetworkServiceFactory
import data.remote.utils.TokenAuthenticator
import data.repositories.auth.AuthUserRepository
import data.repositories.auth.AuthUserRepositoryImpl
import okhttp3.Authenticator
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AuthModule {
    @Provides
    @Singleton
    fun provideAuthAPI(): AuthAPI = NetworkServiceFactory.getApi("${BuildConfig.API_BASE_URL}/api/v1/auth/", AuthAPI::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthAPI): AuthUserRepository = AuthUserRepositoryImpl(authApi)

    @Provides
    @Singleton
    fun provideAccessTokenInterceptor(tokenProvider: TokenProvider): Interceptor = AccessTokenInterceptor(tokenProvider)

    @Provides
    @Singleton
    fun provideTokenAuthenticator(tokenProvider: TokenProvider, authRepository: AuthUserRepository): Authenticator = TokenAuthenticator(tokenProvider = tokenProvider, authRepository = authRepository)



}