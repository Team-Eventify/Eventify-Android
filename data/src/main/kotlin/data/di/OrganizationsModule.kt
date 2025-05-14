package data.di

import com.example.eventify.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.remote.api.OrganizationsAPI
import data.remote.utils.AccessTokenInterceptor
import data.remote.utils.TokenAuthenticator
import data.repositories.organizations.OrganizationsRepository
import data.repositories.organizations.OrganizationsRepositoryImpl
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
            .baseUrl("https://eventify.website/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(OrganizationsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideOrganizationRepository(dataSource: OrganizationsAPI): OrganizationsRepository = OrganizationsRepositoryImpl(dataSource)
}