package feature.profile.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.profile.api.ProfileEntry
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(ProfileEntry::class)
    abstract fun bindProfileEntry(feature: ProfileEntryImpl): FeatureEntry

}