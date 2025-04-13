package com.example.eventify.data.exceptions

fun Throwable.isUnauthorized(): Boolean {
    return this is NetworkException && this.error.status == 401
}

fun Throwable.isNotFound(): Boolean {
    return this is NetworkException && this.error.status == 404
}
