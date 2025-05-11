package ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import core.common.navigation.ComposableFeatureEntry
import core.common.navigation.EventsRoot
import feature.eventFeed.api.EventFeedPath

fun NavGraphBuilder.addEventsNavGraph(
    navController: NavHostController,
    features: List<ComposableFeatureEntry>,
) {
    navigation(
        route = EventFeedPath.baseRoute,
        startDestination = EventsRoot.baseRoute
    ) {
        features.forEach { feature ->
            with(feature) {
                featureComposable(navController)
            }
        }
    }
}