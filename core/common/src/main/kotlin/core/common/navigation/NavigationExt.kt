package core.common.navigation

import androidx.navigation.NavController
import androidx.navigation.navOptions

/**
 * Очищает стек до нужного пункта назначения
 * @param route пункт назначения, к которому осуществляется переход
 * @param popUpTo baseRoute класса BaseDestination - пункт назначения, до которого необходимо очистить стэк
 * @param inclusive должен ли пункт назначения popUpTo быть извлечен из заднего стека
 */
fun NavController.navigateNewTask(
    route: String,
    popUpTo: String = route,
    inclusive: Boolean = true
) {
    navigate(
        route = route,
        navOptions = navOptions {
            popUpTo(popUpTo) { this.inclusive = inclusive }
            launchSingleTop = true
        })
}

fun <T> NavController.subscribeResult(key: String) =
    currentBackStackEntry?.savedStateHandle?.getStateFlow<T?>(key, null)

fun <T> NavController.setNavigationResult(key: String, result: T) {
    previousBackStackEntry?.savedStateHandle?.set(key, result)
}