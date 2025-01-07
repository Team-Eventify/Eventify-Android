package com.example.eventify.presentation.ui.auth.onboarding


/**
 * UI State that represents OnBoardingScreen
 **/
class OnBoardingState

/**
 * OnBoarding Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class OnBoardingActions(
    val onLeaveFromOnboarding: () -> Unit = {}
)


