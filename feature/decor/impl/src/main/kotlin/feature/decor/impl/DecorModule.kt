package feature.decor.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.decor.api.DecorEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DecorModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(DecorEntry::class)
    abstract fun bindProfileEditEntry(feature: DecorEntryImpl): FeatureEntry
}