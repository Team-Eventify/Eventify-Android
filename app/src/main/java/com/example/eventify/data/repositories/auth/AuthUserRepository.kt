package com.example.eventify.data.repositories.auth

import com.example.eventify.data.models.TokenData
import com.example.eventify.data.models.UserCreate
import com.example.eventify.data.models.UserCredentials
import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.data.remote.models.auth.LogInResponse
import com.example.eventify.data.remote.models.auth.PublicKeyInfoResponse
import com.example.eventify.data.remote.models.auth.RefreshTokenRequestData
import com.example.eventify.data.remote.models.auth.RefreshTokenResponse
import com.example.eventify.data.remote.models.auth.RegisterResponse
import com.example.eventify.data.remote.models.auth.RegisterUserRequestData
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import retrofit2.Response

interface AuthUserRepository {
    suspend fun refreshAccessToken(refreshToken: String): Result<TokenData, DataError>
    suspend fun registerUser(user: UserCreate): Result<TokenData, DataError>
    suspend fun logInUser(credentials: UserCredentials): Result<TokenData, DataError>
}

