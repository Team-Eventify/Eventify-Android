package com.example.eventify.presentation.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import javax.inject.Inject


typealias Features = Map<Class<out FeatureEntry>, @JvmSuppressWildcards FeatureEntry>

class FeaturesProvider @Inject constructor(val features: Features) {

    inline fun <reified Feature : ComposableFeatureEntry> NavController.navigateTo(
        vararg arguments: Pair<String, Any?>
    ) = features.findOrNull<Feature>()?.let { navigate(it.destination.toRoute(*arguments)) }

    inline fun <reified Feature : ComposableFeatureEntry, reified PopUpTo : ComposableFeatureEntry> NavController.navigateNewTask(
        vararg arguments: Pair<String, Any?>,
        inclusive: Boolean = true,
    ) = features.findOrNull<Feature>()?.let { destFeature ->
        features.findOrNull<PopUpTo>()?.let { popUpToFeature ->
            navigateNewTask(
                destFeature.destination.toRoute(*arguments),
                popUpToFeature.destination.baseRoute,
                inclusive
            )
        }
    }

    inline fun <reified Feature : ComposableFeatureEntry> NavController.containsInBackStack(): Boolean {
        return features.findOrNull<Feature>()?.let {
            try {
                this.getBackStackEntry(it.destination.baseRoute)
                true
            } catch (e: IllegalArgumentException) {
                false
            }

        } ?: false
    }

}

val LocalFeaturesProvider = compositionLocalOf<FeaturesProvider> { error("No app provider found!") }

inline fun <reified T : FeatureEntry> Features.find(): T =
    findOrNull() ?: error("Unable to find '${T::class.java}' feature.")

inline fun <reified T : FeatureEntry> Features.findOrNull(): T? = this[T::class.java] as? T

inline fun <reified T : ComposableFeatureEntry> Features.navigateToFeature(
    navController: NavController,
    vararg arguments: Pair<String, Any?>
) = this.findOrNull<T>()?.let { navController.navigate(it.destination.toRoute(*arguments)) { launchSingleTop = true } }

inline fun <reified T : ComposableFeatureEntry, reified B : ComposableFeatureEntry> Features.navigateNewTaskFeature(
    navController: NavController,
    vararg arguments: Pair<String, Any?>,
    inclusive: Boolean = true,
) = this.findOrNull<T>()?.let { destFeature ->
    this.findOrNull<B>()?.let { popUpToFeature ->
        navController.navigateNewTask(
            destFeature.destination.toRoute(*arguments),
            popUpToFeature.destination.baseRoute,
            inclusive
        )
    }
}

inline fun <reified T : ComposableFeatureEntry> Features.clearNavigate(
    navController: NavController,
    vararg arguments: Pair<String, Any?>,
) = this.findOrNull<T>()?.let { destFeature ->
    navController.navigate(destFeature.destination.toRoute(*arguments)) {
        popUpTo(0) { inclusive = true } // Удаляет весь стек
        launchSingleTop = true
    }
}