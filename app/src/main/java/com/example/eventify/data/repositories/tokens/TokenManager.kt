package com.example.eventify.data.repositories.tokens

interface TokenManager {
    fun getAccessToken(): String?
    fun setAccessToken(token: String): Unit

    fun getRefreshToken(): String?
    fun setRefreshToken(token: String): Unit

    fun clear(): Unit

}