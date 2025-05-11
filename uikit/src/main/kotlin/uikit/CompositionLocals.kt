package uikit

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.staticCompositionLocalOf
import coil3.ImageLoader


val LocaleSnackbarState = staticCompositionLocalOf<SnackbarHostState> {
    error("No SnackbarHostState not provided")
}

val LocaleImageLoader = staticCompositionLocalOf<ImageLoader> {
    error("No ImageLoader provided")
}
