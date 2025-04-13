package com.example.eventify.presentation.ui.auth.login.state

sealed class SideEffect {
    data object SuccessLogIn : SideEffect()
    data object ServerError : SideEffect()
    data object UnsuccessLogIn : SideEffect()
}