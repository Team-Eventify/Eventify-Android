package di

import core.featureManager.Features
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.Multibinds

@Module
@InstallIn(SingletonComponent::class)
abstract class FeaturesModule {

    @Multibinds
    abstract fun features(): Features

}