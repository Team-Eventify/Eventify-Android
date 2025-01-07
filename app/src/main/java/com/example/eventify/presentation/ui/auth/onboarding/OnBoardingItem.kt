package com.example.eventify.presentation.ui.auth.onboarding

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource

@Stable
data class OnBoardingItem(
    val primaryImage: Painter,
    val title: String,
    val body: String? = null,
    val primaryButtonText: String
)
