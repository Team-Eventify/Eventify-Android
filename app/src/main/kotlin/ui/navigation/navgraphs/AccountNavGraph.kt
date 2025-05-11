package ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import core.common.navigation.AccountRoot
import core.common.navigation.ComposableFeatureEntry
import feature.profile.api.ProfilePath


fun NavGraphBuilder.addAccountNavGraph(
    navController: NavHostController,
    features: List<ComposableFeatureEntry>,
) {
    navigation(
        route = ProfilePath.baseRoute,
        startDestination = AccountRoot.baseRoute
    ) {
        features.forEach { feature ->
            with(feature) {
                featureComposable(navController)
            }
        }
    }
}
