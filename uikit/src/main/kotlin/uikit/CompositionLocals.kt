package uikit

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.staticCompositionLocalOf


val LocaleSnackbarState = staticCompositionLocalOf<SnackbarHostState> {
    error("No SnackbarHostState not provided")
}
