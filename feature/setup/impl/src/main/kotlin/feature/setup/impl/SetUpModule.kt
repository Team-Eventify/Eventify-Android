package feature.setup.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.setup.api.SetUpEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SetUpModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(SetUpEntry::class)
    abstract fun bindSetUpEntry(feature: SetUpEntryImpl): FeatureEntry
}