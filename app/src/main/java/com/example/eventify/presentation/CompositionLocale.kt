package com.example.eventify.presentation

import androidx.compose.runtime.staticCompositionLocalOf
import coil3.ImageLoader


val LocaleImageLoader = staticCompositionLocalOf<ImageLoader> {
    error("No ImageLoader provided")
}