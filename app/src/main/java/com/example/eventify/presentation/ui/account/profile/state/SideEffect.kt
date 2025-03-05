package com.example.eventify.presentation.ui.account.profile.state

sealed class SideEffect {
    data object LogOut : SideEffect()
}