package ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import core.common.navigation.AuthRoot
import core.common.navigation.ComposableFeatureEntry
import feature.login.api.LoginPath


fun NavGraphBuilder.addAuthNavGraph(
    navController: NavHostController,
    features: List<ComposableFeatureEntry>,
    ) {
    navigation(
        route = AuthRoot.baseRoute,
        startDestination = LoginPath.baseRoute
    ) {
        features.forEach { feature ->
            with(feature) {
                featureComposable(navController)
            }
        }
    }
}
