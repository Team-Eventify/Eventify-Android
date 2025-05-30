package core.common.exceptions

import kotlinx.serialization.Serializable


@Serializable
data class NetworkError(
    val status: Int,
    val reasonCode: Int = 0,
    val title: String? = null,
    val retryAfter: String? = null
)

class NetworkException(val error: NetworkError) : Exception(error.title)

val UnauthorizedException = NetworkException(
    NetworkError(
        status = 401,
    )
)

val ForbiddenException = NetworkException(
    NetworkError(
        status = 401,
    )
)

val NotFoundException = NetworkException(
    NetworkError(
        status = 404,
    )
)

val ConflictException = NetworkException(
    NetworkError(
        status = 409,
    )
)

