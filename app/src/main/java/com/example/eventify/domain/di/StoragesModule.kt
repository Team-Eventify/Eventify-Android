package com.example.eventify.domain.di

import android.content.Context
import com.example.eventify.data.storages.EncryptedStorage
import com.example.eventify.data.storages.EncryptedStorageImpl
import com.example.eventify.data.storages.LocaleStorage
import com.example.eventify.data.storages.SharedPreferencesStorageImpl
import com.example.eventify.data.storages.SharedStorage
import com.example.eventify.data.storages.Storages
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object StoragesModule {

    @EncryptedStorage
    @Provides
    @Singleton
    fun provideEncryptedStorage(
        @ApplicationContext context: Context
    ) : LocaleStorage {
        return EncryptedStorageImpl(
            context = context,
            gson = GsonBuilder().create(),
            storageName = Storages.ENCRYPTED,
        )
    }

    @SharedStorage
    @Provides
    @Singleton
    fun provideSharedStorage(
        @ApplicationContext context: Context
    ) : LocaleStorage {
        return SharedPreferencesStorageImpl(
            context = context,
            gson = GsonBuilder().create(),
            storageName = Storages.SHARED
        )
    }
}
