package com.example.eventify.presentation.ui.auth.register.state

interface RegisterListener {
    fun navigateToLogIn(): Unit
    fun onChangeLogin(login: String): Unit
    fun onChangePassword(password: String): Unit
    fun onRequestOtp(login: String, password: String): Unit
    fun onRegister(login: String, password: String, otp: String): Unit
    fun onChangeOtp(otpValue: String): Unit
    fun onTriggerOtpBottomSheet(value: Boolean): Unit
    fun goToPrivacyPolicy(): Unit
}