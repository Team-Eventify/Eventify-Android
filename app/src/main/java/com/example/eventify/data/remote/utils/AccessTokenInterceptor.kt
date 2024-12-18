package com.example.eventify.data.remote.utils

import com.example.eventify.data.repositories.tokens.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val tokenManager: TokenManager
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        if (chain.request().isAuthRequired()){
            val token = tokenManager.getAccessToken() ?: ""
            request.addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
        }

        return chain.proceed(request.build())
    }


    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }
}