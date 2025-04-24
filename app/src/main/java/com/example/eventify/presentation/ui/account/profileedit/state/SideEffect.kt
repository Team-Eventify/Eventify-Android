package com.example.eventify.presentation.ui.account.profileedit.state

sealed class SideEffect {
    data object SuccessUpdate : SideEffect()
    data class FailUpdate(val message: String? = null) : SideEffect()
    data object AccountDeleted : SideEffect()
    data object FailedDeleteAccount : SideEffect()
}