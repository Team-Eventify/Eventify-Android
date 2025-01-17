package com.example.eventify.presentation

import android.app.Application
import com.example.eventify.BuildConfig
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig
import io.appmetrica.analytics.push.AppMetricaPush
import timber.log.Timber


@HiltAndroidApp
class EventifyApp: Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        // Creating an extended library configuration.
        val config = AppMetricaConfig.newConfigBuilder(BuildConfig.APPMETRICA_API_KEY)
            .withCrashReporting(true)
            .build()
        // Initializing the AppMetrica SDK.
        AppMetrica.activate(this, config)
        AppMetricaPush.activate(this)
    }
}