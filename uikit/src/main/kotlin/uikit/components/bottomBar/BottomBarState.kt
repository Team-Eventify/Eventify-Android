package uikit.components.bottomBar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable

@Stable
data class BottomBarItem(
    @DrawableRes val iconResId: Int,
    @StringRes val titleResId: Int,
    val route: String,
    val nestedRoutes: List<String> = emptyList()
) {
    fun contains(value: String?): Boolean {
        return value == route || value in nestedRoutes
    }
}

