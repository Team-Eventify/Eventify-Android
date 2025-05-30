package data.remote.models.auth

internal data class JWK(
    val crv: String,
    val kty: String,
    val x: String,
    val y: String
)