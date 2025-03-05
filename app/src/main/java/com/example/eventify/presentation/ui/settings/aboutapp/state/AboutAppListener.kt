package com.example.eventify.presentation.ui.settings.aboutapp.state

interface AboutAppListener {
    fun navigateUp(): Unit
    fun goToAboutUs(): Unit
    fun goPrivacyPolicy(): Unit
    fun goTermsOfUse(): Unit
    fun goToInformationSecurity(): Unit
    fun goToDonate(): Unit
}