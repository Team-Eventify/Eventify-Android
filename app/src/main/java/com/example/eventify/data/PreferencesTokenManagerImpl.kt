package com.example.eventify.data

import android.app.Activity
import android.content.Context
import com.example.eventify.data.repositories.AuthUserRepository
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class PreferencesTokenManagerImpl @Inject constructor(
    @ActivityContext private val context: Context,
): TokenManager {
    private val sharedPref = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
    override fun getAccessToken(): String? {
        return sharedPref.getString(ACCESS_TOKEN_KEY, null)
    }

    override fun setAccessToken(token: String) {
         with(sharedPref.edit()){
             putString(ACCESS_TOKEN_KEY, token)
             apply()
         }
    }

    override fun getRefreshToken(): String? {
        return sharedPref.getString(REFRESH_TOKEN_KEY, null)
    }

    override fun setRefreshToken(token: String) {
        with(sharedPref.edit()){
            putString(REFRESH_TOKEN_KEY, token)
            apply()
        }
    }

    override fun clear() {
        with(sharedPref.edit()){
            clear()
            apply()
        }
    }

    companion object {
        private const val ACCESS_TOKEN_KEY = "access_token"
        private const val REFRESH_TOKEN_KEY = "refresh_token"
        private const val PREFERENCE_KEY = "tokens"
    }
}