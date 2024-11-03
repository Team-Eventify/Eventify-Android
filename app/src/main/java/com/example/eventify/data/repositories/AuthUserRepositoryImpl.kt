package com.example.eventify.data.repositories

import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.data.remote.models.auth.LogInResponse
import com.example.eventify.data.remote.models.auth.PublicKeyInfoResponse
import com.example.eventify.data.remote.models.auth.RefreshTokenRequestData
import com.example.eventify.data.remote.models.auth.RefreshTokenResponse
import com.example.eventify.data.remote.models.auth.RegisterResponse
import com.example.eventify.data.remote.models.auth.RegisterUserRequestData
import retrofit2.Response

class AuthUserRepositoryImpl(val dataSource: AuthAPI): AuthUserRepository {
    override suspend fun refreshAccessToken(data: RefreshTokenRequestData): Response<RefreshTokenResponse> = dataSource.refreshAccessToken(data)

    override suspend fun registerUser(user: RegisterUserRequestData): Response<RegisterResponse> = dataSource.registerUser(user)
    override suspend fun logInUser(payload: LogInRequestData): Response<LogInResponse> = dataSource.logInUser(payload)

    override suspend fun getPublicKey(): Response<PublicKeyInfoResponse> = dataSource.getPublicKey()
}