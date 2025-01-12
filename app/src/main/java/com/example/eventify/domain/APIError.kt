package com.example.eventify.domain

interface DataError: Error {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN,
        NOT_FOUND,
        BAD_REQUEST,
        FORBIDDEN,
        UNAUTHORIZED,
        RESOURCE_CONFLICT
    }
    enum class Local: DataError {
        DISK_FULL
    }
}
