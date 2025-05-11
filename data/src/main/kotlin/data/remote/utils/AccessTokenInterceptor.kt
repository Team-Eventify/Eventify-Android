package data.remote.utils

import core.data.repositories.tokens.TokenProvider
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val tokenProvider: TokenProvider
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        // TODO devide realization to with and without auth required checking
//        if (chain.request().isAuthRequired()){
        val token = tokenProvider.getAccessToken() ?: ""
        request.addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
//        }

        return chain.proceed(request.build())
    }


    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }
}