package com.example.eventify.presentation.ui.auth.onboarding.state

sealed class SideEffect {
    data object FinishOnBoarding : SideEffect()
}