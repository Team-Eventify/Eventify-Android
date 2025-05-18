package feature.decor.impl.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import core.common.theme.ThemeType
import com.example.eventify.uikit.R as UiKitR
import com.example.eventify.feature.decor.impl.R as DecorR


data class DecorUiState(
    val currentTheme: ThemeType = ThemeType.SYSTEM,
)

enum class ThemeTypePreviews(
    val themeType: ThemeType,
    @DrawableRes val iconResId: Int,
    @StringRes val titleResId: Int,
) {
    SYSTEM(
        themeType = ThemeType.SYSTEM,
        iconResId = UiKitR.drawable.circle_lefthalf_filled,
        titleResId = DecorR.string.system_theme_ttile,
    ),

    DARK(
        themeType = ThemeType.DARK,
        iconResId = UiKitR.drawable.moon_fill,
        titleResId = DecorR.string.dark_theme_title,
        ),

    LIGHT(
        themeType = ThemeType.LIGHT,
        iconResId = UiKitR.drawable.sun_max_fill,
        titleResId = DecorR.string.light_theme_title,
        )
}