package com.example.eventify.domain.di

import com.example.eventify.BuildConfig
import com.example.eventify.data.remote.api.CategoryAPI
import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.remote.utils.AccessTokenInterceptor
import com.example.eventify.data.remote.utils.TokenAuthenticator
import com.example.eventify.data.repositories.category.CategoryRepository
import com.example.eventify.data.repositories.category.CategoryRepositoryImpl
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
object CategoryModule {

    @Provides
    @Singleton
    fun provideCategoriesAPI(accessTokenInterceptor: AccessTokenInterceptor, tokenAuthenticator: TokenAuthenticator): CategoryAPI{
        val client = OkHttpClient
            .Builder()
            .addInterceptor(accessTokenInterceptor)
            .authenticator(tokenAuthenticator)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl("${BuildConfig.API_BASE_URL}/api/v1/category/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CategoryAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepository(categoryApi: CategoryAPI): CategoryRepository = CategoryRepositoryImpl(categoryApi)
}