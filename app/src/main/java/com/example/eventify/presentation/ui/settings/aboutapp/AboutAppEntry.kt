package com.example.eventify.presentation.ui.settings.aboutapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import javax.inject.Inject


val SettingsRootPath = BaseDestination("settings")

val AboutAppPath = SettingsRootPath.child("about-app")

interface AboutAppEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = AboutAppPath
}

class AboutAppEntryImpl @Inject constructor(): AboutAppEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        AboutAppRoute(navController)
    }

}