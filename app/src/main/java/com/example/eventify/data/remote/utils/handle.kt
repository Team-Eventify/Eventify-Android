package com.example.eventify.data.remote.utils

import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import retrofit2.Response


fun <T, R>Response<T>.handle(configure: (HandleContext<T, R>.() -> Unit)? = null): Result<R, DataError.Network>{
    val context = HandleContext<T, R>(this)
    configure?.let { context.it() }
    return context.execute()
}

class HandleContext<T, R>(
    private val response: Response<T>
){
    private val processors = mutableMapOf<Int, (T?) -> Result<R, DataError.Network>>()
    private var transformer: ((T) -> R)? = null

    fun process(code: Int, handler: (T?) -> Result<R, DataError.Network>){
        processors[code] = handler
    }

    fun transformSuccess(handler: (T) -> R){
        transformer = handler
    }

    internal fun execute(): Result<R, DataError.Network> {
        try{
            val statusCode = response.code()
            val body = response.body()

            processors[statusCode]?.invoke(body)?.let {
                return it
            }

            return handleDefaults(statusCode, body)

        } catch (e: Exception){
            return Result.Error(DataError.Network.UNKNOWN)
        }


    }

    private fun handleDefaults(statusCode: Int, body: T?): Result<R, DataError.Network> {
        return when (statusCode) {
            in 200..399 -> body?.let { responseBody ->
                val transformedData = (transformer?.invoke(responseBody) ?: responseBody) as R
                Result.Success(transformedData)
            } ?: Result.Error(DataError.Network.NOT_FOUND)

            401 -> Result.Error(DataError.Network.UNAUTHORIZED)
            403 -> Result.Error(DataError.Network.FORBIDDEN)
            404 -> Result.Error(DataError.Network.NOT_FOUND)
            409 -> Result.Error(DataError.Network.RESOURCE_CONFLICT)
            in 500..Int.MAX_VALUE -> Result.Error(DataError.Network.SERVER_ERROR)
            else -> Result.Error(DataError.Network.UNKNOWN)
        }
    }
}