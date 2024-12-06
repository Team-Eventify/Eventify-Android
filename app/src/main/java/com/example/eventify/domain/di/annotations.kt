package com.example.eventify.domain.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockedEventsRepository


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RequestsSessionManager

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DecodeSessionManager