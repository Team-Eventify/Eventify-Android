package ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import core.common.navigation.ComposableFeatureEntry
import feature.aboutApp.api.AboutAppPath
import feature.aboutApp.api.SettingsRootPath

fun NavGraphBuilder.addSettingsNavGraph(
    navController: NavHostController,
    features: List<ComposableFeatureEntry>,
) {
    navigation(
        route = SettingsRootPath.baseRoute,
        startDestination = AboutAppPath.baseRoute
    ) {
        features.forEach { feature ->
            with(feature) {
                featureComposable(navController)
            }
        }
    }
}
