package feature.aboutApp.api

import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry


val SettingsRootPath = BaseDestination("settings")

val AboutAppPath = SettingsRootPath.child("about-app")

interface AboutAppEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = AboutAppPath
}

