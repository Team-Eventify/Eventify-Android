package domain.models

import data.models.User

data class UserShortInfo(
    val id: String,
    val firstName: String, 
    val lastName: String, 
    val email: String,
)

fun User.toShort() = UserShortInfo(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email
)