package com.example.eventify.presentation.ui.auth.login.state

interface LoginListener {
    fun onChangeLogin(login: String): Unit
    fun onChangePassword(password: String): Unit
    fun onSubmit(): Unit
    fun navigateToRegister(): Unit
    fun navigateToResetPassword(sharedEmail: String? = null): Unit
}