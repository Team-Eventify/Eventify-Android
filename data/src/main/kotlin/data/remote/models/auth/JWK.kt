package data.remote.models.auth

data class JWK(
    val crv: String,
    val kty: String,
    val x: String,
    val y: String
)