package com.example.eventify.presentation.ui.auth.register.state


sealed class SideEffect {
    data object SuccessRegister : SideEffect()
    data class FailRegister(val message: String? = null) : SideEffect()
    data object ServerError : SideEffect()
}