package data.di

import com.example.eventify.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.remote.api.FilesAPI
import data.remote.utils.AccessTokenInterceptor
import data.remote.utils.TokenAuthenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object FilesModule {

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
            .baseUrl("${BuildConfig.API_BASE_URL}/api/v1/files/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(FilesAPI::class.java)
    }

}