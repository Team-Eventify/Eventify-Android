package core.common

import android.content.Context
import com.google.gson.GsonBuilder
import core.common.secure.tokens.EncryptedTokenProviderImpl
import core.common.secure.tokens.TokenProvider
import core.common.storages.EncryptedStorage
import core.common.storages.EncryptedStorageImpl
import core.common.storages.LocaleStorage
import core.common.storages.SharedPreferencesStorageImpl
import core.common.storages.SharedStorage
import core.common.storages.Storages
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreCommonModule {

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
//
    @Provides
    @Singleton
    fun provideTokenManager(
        @EncryptedStorage localeStorage: LocaleStorage,
    ): TokenProvider = EncryptedTokenProviderImpl(
        localeStorage = localeStorage,
    )
}
