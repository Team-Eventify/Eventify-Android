package feature.onboarding.impl

import core.common.annotations.FeatureEntryKey
import core.common.navigation.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import feature.onboarding.api.OnBoardingEntry
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class OnboardingModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(OnBoardingEntry::class)
    abstract fun bindOnBoardingEntry(feature: OnBoardingEntryImpl): FeatureEntry
}