package com.example.eventify.presentation.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.settings.aboutapp.AboutAppPath
import com.example.eventify.presentation.ui.settings.aboutapp.SettingsRootPath

fun NavGraphBuilder.addSettingsNavGraph(
    navController: NavHostController,
    features: List<ComposableFeatureEntry>,
) {
    navigation(
        route = SettingsRootPath.baseRoute,
        startDestination = AboutAppPath
    ) {
        features.forEach { feature ->
            with(feature) {
                featureComposable(navController)
            }
        }
    }
}
