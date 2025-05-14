package feature.search.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.search.api.SearchEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(SearchEntry::class)
    abstract fun bindSearchEntry(feature: SearchEntryImpl): FeatureEntry
}