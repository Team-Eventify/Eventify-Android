package com.example.eventify.presentation.ui.account.aboutapp

import com.example.eventify.presentation.utils.UiText


/**
 * UI State that represents AboutAppScreen
 **/
data class AboutAppState(
    val versionName: UiText = UiText.DynamicString("Unknown"),
)

/**
 * AboutApp Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class AboutAppActions(
    val navigateUp: () -> Unit = {},
    val goToAboutUs: () -> Unit = {},
    val goPrivacyPolicy: () -> Unit = {},
    val goTermsOfUse: () -> Unit = {},
    val goToInformationSecurity: () -> Unit = {},
    val goToDonate: () -> Unit = {},
)


