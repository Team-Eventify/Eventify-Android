package com.example.eventify.domain.di

import com.example.eventify.data.remote.api.OrganizationsAPI
import com.example.eventify.data.remote.utils.AccessTokenInterceptor
import com.example.eventify.data.remote.utils.TokenAuthenticator
import dagger.Module
import com.example.eventify.BuildConfig
import com.example.eventify.data.repositories.organizations.OrganizationsRepository
import com.example.eventify.data.repositories.organizations.OrganizationsRepositoryImpl
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
object OrganizationsModule {
    @Provides
    @Singleton
    fun provideOrganizationsAPI(accessTokenInterceptor: AccessTokenInterceptor, tokenAuthenticator: TokenAuthenticator): OrganizationsAPI {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(accessTokenInterceptor)
            .authenticator(tokenAuthenticator)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl("${BuildConfig.API_BASE_URL}/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(OrganizationsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideOrganizationRepository(dataSource: OrganizationsAPI): OrganizationsRepository = OrganizationsRepositoryImpl(dataSource)
}