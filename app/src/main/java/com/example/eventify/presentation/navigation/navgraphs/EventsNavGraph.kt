package com.example.eventify.presentation.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.events.eventsfeed.EventFeedPath
import com.example.eventify.presentation.ui.events.eventsfeed.EventsRootPath

fun NavGraphBuilder.addEventsNavGraph(
    navController: NavHostController,
    features: List<ComposableFeatureEntry>,
) {
    navigation(
        route = EventsRootPath.baseRoute,
        startDestination = EventFeedPath
    ) {
        features.forEach { feature ->
            with(feature) {
                featureComposable(navController)
            }
        }
    }
}