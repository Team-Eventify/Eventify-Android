package com.example.eventify.data.repositories.auth

import com.example.eventify.domain.models.TokenData
import com.example.eventify.domain.models.UserCreate
import com.example.eventify.domain.models.UserCredentials
import com.example.eventify.domain.models.OtpUserCreate
import com.example.eventify.domain.models.RegisterValidationData

interface AuthUserRepository {
    suspend fun refreshAccessToken(refreshToken: String): TokenData
    suspend fun registerUser(user: UserCreate): TokenData
    suspend fun logInUser(credentials: UserCredentials): TokenData
    suspend fun validateRegisterData(data: RegisterValidationData): String
    suspend fun otpRegisterUser(user: OtpUserCreate): TokenData
}

