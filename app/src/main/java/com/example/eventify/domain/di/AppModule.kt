package com.example.eventify.domain.di

import android.content.Context
import com.example.eventify.data.PreferencesTokenManagerImpl
import com.example.eventify.data.TokenManager
import com.example.eventify.data.remote.AccessTokenInterceptor
import com.example.eventify.data.remote.NetworkServiceFactory
import com.example.eventify.data.remote.TokenAuthenticator
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.repositories.AuthUserRepository
import com.example.eventify.data.repositories.AuthUserRepositoryImpl
import com.example.eventify.data.repositories.EventRepositoryImpl
import com.example.eventify.data.repositories.EventsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule