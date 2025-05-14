package feature.aboutApp.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.aboutApp.api.AboutAppEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AboutAppModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(AboutAppEntry::class)
    abstract fun bindAboutAppEntry(feature: AboutAppEntryImpl): FeatureEntry
}