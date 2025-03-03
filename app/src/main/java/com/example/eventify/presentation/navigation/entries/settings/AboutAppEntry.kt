package com.example.eventify.presentation.navigation.entries.settings

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.entries.BaseDestination
import com.example.eventify.presentation.navigation.entries.ComposableFeatureEntry
import com.example.eventify.presentation.ui.account.aboutapp.AboutAppRoute
import javax.inject.Inject


val SettingsRootPath = BaseDestination("settings")

val AboutAppPath = SettingsRootPath.updateAndGetPath("about-app")

interface AboutAppEntry : ComposableFeatureEntry {
    override val route: String
        get() = AboutAppPath

    override val argumentsKeys: List<String>
        get() = emptyList()
}

class AboutAppEntryImpl @Inject constructor(): AboutAppEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        AboutAppRoute(navController)
    }

}