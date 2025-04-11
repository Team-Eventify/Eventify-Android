package com.example.eventify.presentation.ui.account.profile_decor.state

sealed class SideEffect {
    data object SuccessUpdate : SideEffect()
    data class FailUpdate(val message: String? = null) : SideEffect()
}