package com.example.eventify.data.repositories.tokens

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject
import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT

private const val ACCESS_TOKEN_KEY = "access_token"
private const val REFRESH_TOKEN_KEY = "refresh_token"
private const val USER_ID_KEY = "user_id"
private const val PREFERENCE_KEY = "tokens"

fun Context.getTokensPreferences(): SharedPreferences = this.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)

class PreferencesTokenManagerImpl @Inject constructor(
    @ActivityContext private val context: Context,
): TokenManager {
    private val sharedPref = context.getTokensPreferences()
    override fun getAccessToken(): String? = sharedPref.getString(ACCESS_TOKEN_KEY, null)

    override fun setAccessToken(token: String) {
         with(sharedPref.edit()){
             putString(ACCESS_TOKEN_KEY, token)
             apply()
         }
    }

    override fun getRefreshToken(): String? = sharedPref.getString(REFRESH_TOKEN_KEY, null)

    override fun setRefreshToken(token: String) {
        with(sharedPref.edit()){
            putString(REFRESH_TOKEN_KEY, token)
            apply()
        }
    }

    override fun getUserId(): String? = sharedPref.getString(USER_ID_KEY, null)

    override fun setUserId(value: String) {
        with(sharedPref.edit()){
            putString(USER_ID_KEY, value)
            apply()
        }
    }

    override fun clear() {
        with(sharedPref.edit()){
            clear()
            apply()
        }
    }

    override fun isValidData(): Boolean {
        val accessToken = getAccessToken()?.let { decodeToken(it) } ?: return false
        val refreshToken = getRefreshToken()?.let { decodeToken(it) } ?: return false
        getUserId() ?: return false

        val currentDate = System.currentTimeMillis()

        return !(accessToken.expiresAt.time < currentDate && refreshToken.expiresAt.time < currentDate)
    }
    private fun decodeToken(token: String): DecodedJWT? {
        return try {
            JWT.decode(token)
        } catch (e: Exception){
            // TODO write logs
            null
        }
    }
}