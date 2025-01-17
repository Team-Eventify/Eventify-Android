package com.example.eventify.domain.di

import com.example.eventify.BuildConfig
import com.example.eventify.data.remote.utils.AccessTokenInterceptor
import com.example.eventify.data.remote.utils.TokenAuthenticator
import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.repositories.events.EventRepositoryImpl
import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.data.repositories.events.MockedEventRepositoryImpl
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
object EventsModule {
    @Provides
    @Singleton
    fun provideEventsAPI(accessTokenInterceptor: AccessTokenInterceptor, tokenAuthenticator: TokenAuthenticator): EventsAPI {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(accessTokenInterceptor)
            .authenticator(tokenAuthenticator)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl("${BuildConfig.API_BASE_URL}/api/v1/events/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(EventsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideEventsRepository(eventsAPI: EventsAPI): EventsRepository = EventRepositoryImpl(eventsAPI)


    @MockedEventsRepository
    @Provides
    @Singleton
    fun providesMockedEventsRepository(): EventsRepository = MockedEventRepositoryImpl()

}