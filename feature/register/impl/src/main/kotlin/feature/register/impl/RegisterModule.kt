package feature.register.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.register.api.RegisterEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RegisterModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(RegisterEntry::class)
    abstract fun bindRegisterEntry(feature: RegisterEntryImpl): FeatureEntry
}