package com.example.eventify

import android.app.Application
import com.example.eventify.BuildConfig
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

//        val config = AppMetricaConfig.newConfigBuilder("de8b1938-47f7-43a2-b230-4da1d2e2f8cc")
//            .withCrashReporting(true)
//            .build()
//
//        AppMetrica.activate(this, config)
//        AppMetricaPush.activate(this)
    }

}

val Application.featuresProvider: FeaturesProvider
    get() = (this as EventifyApp).featuresProvider