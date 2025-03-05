package com.example.eventify.presentation.ui.auth.resetpassword.state

interface ResetPasswordListener {
    fun submit()
    fun changeEmail(email: String)
}