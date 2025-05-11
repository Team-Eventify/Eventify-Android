package data.remote.utils

import core.common.secure.tokens.TokenProvider
import data.repositories.auth.AuthUserRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import data.remote.utils.isAuthRequired

val Response.responseCount: Int
    get() = generateSequence(this) { it.priorResponse }.count()

class TokenAuthenticator @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val authRepository: AuthUserRepository
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.responseCount >= MAX_RETRIES) return null

        if (!response.request.isAuthRequired()) return null


        val refreshToken = tokenProvider.getRefreshToken() ?: return null

        val newTokensData = runBlocking {
            try {
                authRepository.refreshAccessToken(refreshToken = refreshToken)
            } catch (e: Exception) {
                null
            }
        } ?: return null

        tokenProvider.apply {
            setAccessToken(newTokensData.accessToken)
            setRefreshToken(newTokensData.refreshToken)
            setUserId(newTokensData.userID)
        }



        return response.request.newBuilder()
            .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE ${newTokensData.accessToken}")
            .build()
    }
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
        const val MAX_RETRIES = 4
    }

}