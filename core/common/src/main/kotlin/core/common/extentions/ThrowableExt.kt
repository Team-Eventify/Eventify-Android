package core.common.extentions

import core.common.exceptions.NetworkException


fun Throwable.isUnauthorized(): Boolean {
    return this is NetworkException && this.error.status == 401
}

fun Throwable.isNotFound(): Boolean {
    return this is NetworkException && this.error.status == 404
}

fun Throwable.isNotFoundOrEmpty(): Boolean {
    return this is NetworkException && this.error.status == 404
}

fun Throwable.isServerError(): Boolean {
    return this is NetworkException && this.error.status >= 500
}
