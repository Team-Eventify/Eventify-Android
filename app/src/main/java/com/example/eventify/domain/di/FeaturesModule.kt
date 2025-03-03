package com.example.eventify.domain.di

import com.example.eventify.presentation.navigation.Features
import com.example.eventify.presentation.navigation.entries.FeatureEntry
import com.example.eventify.presentation.navigation.entries.account.ProfileEditEntry
import com.example.eventify.presentation.navigation.entries.account.ProfileEditEntryImpl
import com.example.eventify.presentation.navigation.entries.account.ProfileEntry
import com.example.eventify.presentation.navigation.entries.account.ProfileEntryImpl
import com.example.eventify.presentation.navigation.entries.auth.LoginEntry
import com.example.eventify.presentation.navigation.entries.auth.LoginEntryImpl
import com.example.eventify.presentation.navigation.entries.auth.OnBoardingEntry
import com.example.eventify.presentation.navigation.entries.auth.OnBoardingEntryImpl
import com.example.eventify.presentation.navigation.entries.auth.RegisterEntry
import com.example.eventify.presentation.navigation.entries.auth.RegisterEntryImpl
import com.example.eventify.presentation.navigation.entries.auth.ResetPasswordEntry
import com.example.eventify.presentation.navigation.entries.auth.ResetPasswordEntryImpl
import com.example.eventify.presentation.navigation.entries.events.EventDetailEntry
import com.example.eventify.presentation.navigation.entries.events.EventDetailEntryImpl
import com.example.eventify.presentation.navigation.entries.events.EventsFeedFeatureEntry
import com.example.eventify.presentation.navigation.entries.events.EventsFeedFeatureEntryImpl
import com.example.eventify.presentation.navigation.entries.events.MyEventsEntry
import com.example.eventify.presentation.navigation.entries.events.MyEventsEntryImpl
import com.example.eventify.presentation.navigation.entries.events.SearchEntry
import com.example.eventify.presentation.navigation.entries.events.SearchEntryImpl
import com.example.eventify.presentation.navigation.entries.settings.AboutAppEntry
import com.example.eventify.presentation.navigation.entries.settings.AboutAppEntryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeaturesModule {


//    abstract fun features(): Features
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(LoginEntry::class)
    abstract fun bindLoginEntry(feature: LoginEntryImpl): FeatureEntry

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(RegisterEntry::class)
    abstract fun bindRegisterEntry(feature: RegisterEntryImpl): FeatureEntry

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(OnBoardingEntry::class)
    abstract fun bindOnBoardingEntry(feature: OnBoardingEntryImpl): FeatureEntry

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(ResetPasswordEntry::class)
    abstract fun bindResetPasswordEntry(feature: ResetPasswordEntryImpl): FeatureEntry

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(MyEventsEntry::class)
    abstract fun bindMyEventsEntry(feature: MyEventsEntryImpl): FeatureEntry

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(SearchEntry::class)
    abstract fun bindSearchEntry(feature: SearchEntryImpl): FeatureEntry

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(EventDetailEntry::class)
    abstract fun bindEventDetailEntry(feature: EventDetailEntryImpl): FeatureEntry

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(EventsFeedFeatureEntry::class)
    abstract fun bindEventsFeedFeatureEntry(feature: EventsFeedFeatureEntryImpl): FeatureEntry

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(ProfileEntry::class)
    abstract fun bindProfileEntry(feature: ProfileEntryImpl): FeatureEntry

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(ProfileEditEntry::class)
    abstract fun bindProfileEditEntry(feature: ProfileEditEntryImpl): FeatureEntry

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(AboutAppEntry::class)
    abstract fun bindAboutAppEntry(feature: AboutAppEntryImpl): FeatureEntry

}

