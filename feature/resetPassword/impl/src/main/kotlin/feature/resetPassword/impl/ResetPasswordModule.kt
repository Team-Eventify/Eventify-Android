package feature.resetPassword.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.resetPassword.api.ResetPasswordEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ResetPasswordModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(ResetPasswordEntry::class)
    abstract fun bindResetPasswordEntry(feature: ResetPasswordEntryImpl): FeatureEntry
}