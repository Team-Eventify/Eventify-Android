package feature.login.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.login.api.LoginEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(LoginEntry::class)
    abstract fun bindLoginEntry(feature: LoginEntryImpl): FeatureEntry
}