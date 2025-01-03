package com.example.eventify.data.repositories.auth

import com.example.eventify.domain.models.TokenData
import com.example.eventify.domain.models.UserCreate
import com.example.eventify.domain.models.UserCredentials
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result

interface AuthUserRepository {
    suspend fun refreshAccessToken(refreshToken: String): Result<TokenData, DataError>
    suspend fun registerUser(user: UserCreate): Result<TokenData, DataError>
    suspend fun logInUser(credentials: UserCredentials): Result<TokenData, DataError>
}

