package data.di

import com.example.eventify.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.remote.api.CategoryAPI
import data.remote.utils.AccessTokenInterceptor
import data.remote.utils.TokenAuthenticator
import data.repositories.category.CategoryRepository
import data.repositories.category.CategoryRepositoryImpl
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
    fun provideCategoriesAPI(accessTokenInterceptor: AccessTokenInterceptor, tokenAuthenticator: TokenAuthenticator): CategoryAPI {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(accessTokenInterceptor)
            .authenticator(tokenAuthenticator)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl("https://eventify.website/api/v1/category/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CategoryAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepository(categoryApi: CategoryAPI): CategoryRepository = CategoryRepositoryImpl(categoryApi)
}