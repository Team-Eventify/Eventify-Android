package com.example.eventify.presentation.ui.auth.register.state

interface RegisterListener {
    fun navigateToLogIn(): Unit
    fun onChangeLogin(login: String): Unit
    fun onChangePassword(password: String): Unit
    fun onRequestOtp(): Unit
    fun onRegister(): Unit
    fun onChangeOtp(otpValue: String): Unit
    fun onTriggerOtpBottomSheet(value: Boolean): Unit
    fun goToPrivacyPolicy(): Unit
}