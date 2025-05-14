package data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.remote.api.UsersAPI
import data.remote.utils.AccessTokenInterceptor
import data.remote.utils.TokenAuthenticator
import data.repositories.users.UsersRepository
import data.repositories.users.UsersRepositoryImpl
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
            .baseUrl("https://eventify.website/api/v1/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UsersAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersRepository(usersApi: UsersAPI): UsersRepository = UsersRepositoryImpl(usersApi)
}