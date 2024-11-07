package com.example.eventify.data.remote.utils

import com.example.eventify.data.remote.models.auth.RefreshTokenRequestData
import com.example.eventify.data.repositories.AuthUserRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

val Response.responseCount: Int
    get() = generateSequence(this) { it.priorResponse }.count()

class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val authRepository: AuthUserRepository
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        if (!response.request.isAuthRequired()) {
            return response.request.newBuilder().build()
        }

        val refreshToken = tokenManager.getRefreshToken() ?: return null
        val newTokensData = runBlocking {
            authRepository.refreshAccessToken(data = RefreshTokenRequestData(
                refresh = refreshToken
            )).body()
        } ?: return null

        tokenManager.setAccessToken(newTokensData.accessToken)
        tokenManager.setRefreshToken(newTokensData.refreshToken)

        return response.request.newBuilder()
            .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE ${newTokensData.accessToken}")
            .build()
    }
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
        const val MAX_RETRIES = 3
    }

}