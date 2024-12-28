package com.example.eventify.data.remote.utils

import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import retrofit2.Response

fun <T, V> Response<T>.handle(transform: ((T) -> V)? = null): Result<V, DataError> = try {
    when(this.code()){
        in 200..399 -> this.body()?.let { responseBody ->
            val transformedData = transform?.invoke(responseBody) ?: responseBody
            Result.Success(transformedData as V)
        } ?: Result.Error(DataError.API.NOT_FOUND)
        403 -> Result.Error(DataError.API.FORBIDDEN)
        404 -> Result.Error(DataError.API.NOT_FOUND)
        in 500..Int.MAX_VALUE -> Result.Error(DataError.Network.SERVER_ERROR)
        else -> Result.Error(DataError.Network.UNKNOWN)
    }
} catch (e: Exception){
    Result.Error(DataError.Network.UNKNOWN)
}