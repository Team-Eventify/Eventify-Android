package com.example.eventify.presentation.ui.account.profile.state

interface ProfileListener {
    fun onLogOut(): Unit
    fun navigateToProfileEdit(): Unit
    fun navigateToAppInfo(): Unit
    fun navigateToDecor(): Unit
}