package com.example.eventify.presentation.ui.settings.aboutapp.state

import com.example.eventify.presentation.utils.UiText

data class AboutAppState(
    val versionName: UiText = UiText.DynamicString("Unknown"),
)