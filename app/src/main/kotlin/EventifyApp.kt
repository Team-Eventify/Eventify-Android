package com.example.eventify

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig
import io.appmetrica.analytics.push.AppMetricaPush
import javax.inject.Inject
import core.featureManager.FeaturesProvider


@HiltAndroidApp
class EventifyApp: Application(){
    @Inject
    lateinit var featuresProvider: FeaturesProvider

    override fun onCreate() {
        super.onCreate()

        val config = AppMetricaConfig.newConfigBuilder(BuildConfig.APPMETRICA_API_KEY)
            .withCrashReporting(BuildConfig.ENABLED_CRASH_REPORTING)
            .build()

        AppMetrica.activate(this, config)
        AppMetricaPush.activate(this)
    }

}

val Application.featuresProvider: FeaturesProvider
    get() = (this as EventifyApp).featuresProvider