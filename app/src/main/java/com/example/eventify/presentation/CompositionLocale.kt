package com.example.eventify.presentation

import androidx.compose.runtime.staticCompositionLocalOf
import coil3.ImageLoader
import com.example.eventify.presentation.ui.common.snackbar.AppSnackbarState


val LocaleImageLoader = staticCompositionLocalOf<ImageLoader> {
    error("No ImageLoader provided")
}


val LocalSnackbarState = staticCompositionLocalOf<AppSnackbarState> {
    error("No SnackbarController provided")
}
