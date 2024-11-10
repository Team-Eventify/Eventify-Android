package com.example.eventify.presentation.models



data class UserUiState(
    val email: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val telegramName: String
){
    companion object {
        fun default(): UserUiState = UserUiState(
            firstName = "",
            middleName = "",
            lastName = "",
            telegramName = "",
            email = ""
        )
    }
}


sealed class UserResult {
    object Idle : UserResult()
    object Loading : UserResult()
    object Success : UserResult()
    data class Error(val message: String): UserResult()
}