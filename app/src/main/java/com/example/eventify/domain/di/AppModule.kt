package com.example.eventify.domain.di

import com.example.eventify.presentation.navigation.DefaultNavigator
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.AuthRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNavigator(): Navigator = DefaultNavigator(AuthRouter.LogInRoute)
}