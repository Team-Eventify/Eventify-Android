
package uikit

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.runtime.ProvidedValue


private val DarkColorScheme = darkColorScheme(
    primary = BrandYellow,
    primaryContainer = BrandYellow,
    onPrimary = Color.Black,
    secondaryContainer = BrandYellow,
    secondary = PurpleGrey80,
    onSecondary = Color.White,
    tertiary = Pink80,
    surface = Color(0xFF232326),
    onSurfaceVariant = DarkGray,
    surfaceContainer = Color(0xFF242427),
    errorContainer = Color(0xFFED2B32),
    error = PureRed,
    surfaceVariant = Color(0xFF27272A)
)

private val LightColorScheme = lightColorScheme(
    primary = BrandYellow,
    primaryContainer = BrandYellow,
    onPrimary = Color.Black,
    onSurface = Color.Black,
    secondary = PurpleGrey40,
    onSecondary = Color.Black,
    tertiary = Pink40,
    background = Color(0xFFececec),
    error = Color(0xFFFF8F88),
    errorContainer = Color(0xFFFF8F88),
    onError = Color.Black,
    surfaceContainer = Color(0xFFeeeeee),
    surface = Color.White,
    onSurfaceVariant = Color(0xFF858591),
    surfaceVariant = Color.White,
)

@Composable
fun EventifyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    vararg values: ProvidedValue<*>,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(values = values) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}