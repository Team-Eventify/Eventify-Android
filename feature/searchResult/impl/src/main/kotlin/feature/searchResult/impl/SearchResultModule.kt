package feature.searchResult.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.searchResult.api.SearchDetailEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchResultModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(SearchDetailEntry::class)
    abstract fun bindSearchResultEntry(feature: SearchDetailEntryImpl): FeatureEntry
}