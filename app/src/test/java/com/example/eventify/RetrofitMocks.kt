package com.example.eventify

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response


fun <T>emptyResponseError(
    code: Int
): Response<T> = Response.error(
    code,
    "".toResponseBody("application/json".toMediaType())
)

fun <T>responseJsonError(
    code: Int,
    body: T
): Response<T> {
    val gson = Gson()
    val responseBody = gson
        .toJson(body)
        .toResponseBody("application/json".toMediaType())
    return Response.error(
        code,
        responseBody
    )
}

fun responseTextError(
    code: Int,
    body: String
): Response<String> = Response.error(
    code,
    body.toResponseBody("text/plain".toMediaType())
)

fun <T>responseSuccess(body: T, code: Int = 200): Response<T>{
    return Response.success(code, body)
}