package com.example.eventify.domain.di

import com.example.eventify.presentation.navigation.FeatureEntry
import com.example.eventify.presentation.ui.account.profileedit.ProfileEditEntry
import com.example.eventify.presentation.ui.account.profileedit.ProfileEditEntryImpl
import com.example.eventify.presentation.ui.account.profile.ProfileEntry
import com.example.eventify.presentation.ui.account.profile.ProfileEntryImpl
import com.example.eventify.presentation.ui.auth.login.LoginEntry
import com.example.eventify.presentation.ui.auth.login.LoginEntryImpl
import com.example.eventify.presentation.ui.auth.onboarding.OnBoardingEntry
import com.example.eventify.presentation.ui.auth.onboarding.OnBoardingEntryImpl
import com.example.eventify.presentation.ui.auth.register.RegisterEntry
import com.example.eventify.presentation.ui.auth.register.RegisterEntryImpl
import com.example.eventify.presentation.ui.auth.resetpassword.ResetPasswordEntry
import com.example.eventify.presentation.ui.auth.resetpassword.ResetPasswordEntryImpl
import com.example.eventify.presentation.ui.events.eventdetail.EventDetailEntry
import com.example.eventify.presentation.ui.events.eventdetail.EventDetailEntryImpl
import com.example.eventify.presentation.ui.events.eventsfeed.EventsFeedEntry
import com.example.eventify.presentation.ui.events.eventsfeed.EventsFeedEntryImpl
import com.example.eventify.presentation.ui.events.myevents.MyEventsEntry
import com.example.eventify.presentation.ui.events.myevents.MyEventsEntryImpl
import com.example.eventify.presentation.ui.events.search.SearchEntry
import com.example.eventify.presentation.ui.events.search.SearchEntryImpl
import com.example.eventify.presentation.ui.settings.aboutapp.AboutAppEntry
import com.example.eventify.presentation.ui.settings.aboutapp.AboutAppEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
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
    @FeatureEntryKey(EventsFeedEntry::class)
    abstract fun bindEventsFeedFeatureEntry(feature: EventsFeedEntryImpl): FeatureEntry

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

