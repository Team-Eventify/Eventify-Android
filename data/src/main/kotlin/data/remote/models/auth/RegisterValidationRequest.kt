package data.remote .models.auth

data class RegisterValidationRequest(
    val email: String,
    val password: String
)