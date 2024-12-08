package com.example.eventify.domain.di

import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.remote.api.FilesAPI
import com.example.eventify.data.remote.utils.AccessTokenInterceptor
import com.example.eventify.data.remote.utils.TokenAuthenticator
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
object FilesModule {

    @Provides
    @Singleton
    fun provideFilesApi(accessTokenInterceptor: AccessTokenInterceptor, tokenAuthenticator: TokenAuthenticator): FilesAPI {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(accessTokenInterceptor)
            .authenticator(tokenAuthenticator)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl("https://eventify.website/api/v1/files/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(FilesAPI::class.java)
    }

}