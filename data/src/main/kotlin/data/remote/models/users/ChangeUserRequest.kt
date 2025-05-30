package data.remote.models.users

internal data class ChangeUserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val telegramName: String
)