package com.example.eventify.domain.di

import com.example.eventify.presentation.navigation.FeatureEntry
import dagger.MapKey
import javax.inject.Qualifier
import kotlin.reflect.KClass

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockedEventsRepository


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RequestsSessionManager

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DecodeSessionManager

@MapKey
annotation class FeatureEntryKey(val value: KClass<out FeatureEntry>)
