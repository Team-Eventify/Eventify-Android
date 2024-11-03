package com.example.eventify.data.repositories

import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.data.remote.models.auth.LogInResponse
import com.example.eventify.data.remote.models.auth.PublicKeyInfoResponse
import com.example.eventify.data.remote.models.auth.RefreshTokenRequestData
import com.example.eventify.data.remote.models.auth.RefreshTokenResponse
import com.example.eventify.data.remote.models.auth.RegisterResponse
import com.example.eventify.data.remote.models.auth.RegisterUserRequestData
import retrofit2.Response

interface AuthUserRepository {
    suspend fun refreshAccessToken(data: RefreshTokenRequestData): Response<RefreshTokenResponse>
    suspend fun registerUser(user: RegisterUserRequestData): Response<RegisterResponse>
    suspend fun logInUser(payload: LogInRequestData): Response<LogInResponse>
    suspend fun getPublicKey(): Response<PublicKeyInfoResponse>
}

