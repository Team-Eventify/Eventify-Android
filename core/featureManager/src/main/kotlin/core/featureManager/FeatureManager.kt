package core.featureManager

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import core.common.navigation.ComposableFeatureEntry
import core.common.navigation.FeatureEntry
import core.common.navigation.RouteBuilder
import core.common.navigation.navigateNewTask
import javax.inject.Inject

typealias Features = Map<Class<out FeatureEntry>, @JvmSuppressWildcards FeatureEntry>

class FeaturesProvider @Inject constructor(val features: Features) {

    inline fun <reified Feature : ComposableFeatureEntry> NavController.navigateTo(
        noinline builder: RouteBuilder.() -> Unit = {},
    ) = features.findOrNull<Feature>()?.let { navigate(it.destination.applyBuilder(builder)) }

    inline fun <reified Feature : ComposableFeatureEntry, reified PopUpTo : ComposableFeatureEntry> NavController.navigateNewTask(
        noinline builder: RouteBuilder.() -> Unit = {},
        inclusive: Boolean = true,
    ) = features.findOrNull<Feature>()?.let { destFeature ->
        features.findOrNull<PopUpTo>()?.let { popUpToFeature ->
            navigateNewTask(
                destFeature.destination.applyBuilder(builder),
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
    noinline builder: RouteBuilder.() -> Unit = {},
) = this.findOrNull<T>()?.let {
    navController.navigate(it.destination.applyBuilder(builder)) {
        launchSingleTop = true
    }
}

inline fun <reified T : ComposableFeatureEntry, reified B : ComposableFeatureEntry> Features.navigateNewTaskFeature(
    navController: NavController,
    noinline builder: RouteBuilder.() -> Unit = {},
    inclusive: Boolean = true,
) = this.findOrNull<T>()?.let { destFeature ->
    this.findOrNull<B>()?.let { popUpToFeature ->
        navController.navigateNewTask(
            destFeature.destination.applyBuilder(builder),
            popUpToFeature.destination.baseRoute,
            inclusive
        )
    }
}

inline fun <reified T : ComposableFeatureEntry> Features.clearNavigate(
    navController: NavController,
    noinline builder: RouteBuilder.() -> Unit = {},
) = this.findOrNull<T>()?.let { destFeature ->
    navController.navigate(destFeature.destination.applyBuilder(builder)) {
        popUpTo(0) { inclusive = true } // Удаляет весь стек
        launchSingleTop = true
    }
}

