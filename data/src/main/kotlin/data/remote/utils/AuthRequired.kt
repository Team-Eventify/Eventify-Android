package data.remote.utils

import okhttp3.Request
import retrofit2.Invocation


@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthRequired


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class NoAuthRequired


fun Request.isAuthRequired(): Boolean {
    val method = this.tag(Invocation::class.java)?.method() ?: return false
    val apiClass = method.declaringClass

    if (method.isAnnotationPresent(NoAuthRequired::class.java)) return false
    if (method.isAnnotationPresent(AuthRequired::class.java)) return true

    return apiClass.isAnnotationPresent(AuthRequired::class.java)
}

