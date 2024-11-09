package com.example.eventify.presentation.models



data class UserUiState(
    val firstName: String
){
    companion object {
        fun default(): UserUiState = UserUiState(
            firstName = ""
        )
    }
}


sealed class UserResult {
    object Idle : UserResult()
    object Loading : UserResult()
    object Success : UserResult()
    data class Error(val message: String): UserResult()
}