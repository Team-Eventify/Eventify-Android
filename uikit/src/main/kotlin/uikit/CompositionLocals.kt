package uikit

import androidx.compose.runtime.staticCompositionLocalOf
import coil3.ImageLoader
import uikit.components.snackbar.AppSnackbarState


val LocalSnackbarState = staticCompositionLocalOf<AppSnackbarState> {
    error("No SnackbarHostState not provided")
}

val LocaleImageLoader = staticCompositionLocalOf<ImageLoader> {
    error("No ImageLoader provided")
}
