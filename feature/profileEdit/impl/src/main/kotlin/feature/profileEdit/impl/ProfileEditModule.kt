package feature.profileEdit.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.profileEdit.api.ProfileEditEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileEditModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(ProfileEditEntry::class)
    abstract fun bindProfileEditEntry(feature: ProfileEditEntryImpl): FeatureEntry
}