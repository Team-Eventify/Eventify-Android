package com.example.eventify.presentation.ui.account.aboutapp.state

import com.example.eventify.presentation.utils.UiText

data class AboutAppState(
    val versionName: UiText = UiText.DynamicString("Unknown"),
)