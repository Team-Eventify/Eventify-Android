package com.example.eventify.presentation.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.eventify.presentation.navigation.entries.ComposableFeatureEntry
import com.example.eventify.presentation.navigation.entries.settings.AboutAppPath
import com.example.eventify.presentation.navigation.entries.settings.SettingsRootPath

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
