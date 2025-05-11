package feature.eventDetail.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.eventDetail.api.EventDetailEntry
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class EventDetailModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(EventDetailEntry::class)
    abstract fun bindEventDetailEntry(feature: EventDetailEntryImpl): FeatureEntry
}