package feature.myEvents.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.myEvents.api.MyEventsEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MyEventsModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(MyEventsEntry::class)
    abstract fun bindOnBoardingEntry(feature: MyEventsEntryImpl): FeatureEntry
}