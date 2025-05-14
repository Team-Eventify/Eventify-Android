package data.di

import com.example.eventify.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.remote.api.EventsAPI
import data.remote.utils.AccessTokenInterceptor
import data.remote.utils.TokenAuthenticator
import data.repositories.events.EventRepositoryImpl
import data.repositories.events.EventsRepository
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
            .baseUrl("https://eventify.website/api/v1/events/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(EventsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideEventsRepository(eventsAPI: EventsAPI): EventsRepository = EventRepositoryImpl(eventsAPI)


}