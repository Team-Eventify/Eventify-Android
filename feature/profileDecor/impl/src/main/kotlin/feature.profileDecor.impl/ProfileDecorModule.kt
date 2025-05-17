package feature.profileDecor.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.profileDecor.api.ProfileDecorEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileDecorModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(ProfileDecorEntry::class)
    abstract fun bindProfileEditEntry(feature: ProfileDecorEntryImpl): FeatureEntry
}