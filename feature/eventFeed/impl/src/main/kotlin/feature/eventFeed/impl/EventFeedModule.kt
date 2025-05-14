package feature.eventFeed.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.eventFeed.api.EventsFeedEntry
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class EventFeedModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(EventsFeedEntry::class)
    abstract fun bindEventsFeedFeatureEntry(feature: EventsFeedEntryImpl): FeatureEntry
}