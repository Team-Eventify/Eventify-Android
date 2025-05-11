package data.repositories.auth

import data.models.OtpUserCreate
import data.models.RegisterValidationData
import data.models.TokenData
import data.models.UserCreate
import data.models.UserCredentials

interface AuthUserRepository {
    suspend fun refreshAccessToken(refreshToken: String): TokenData
    suspend fun registerUser(user: UserCreate): TokenData
    suspend fun logInUser(credentials: UserCredentials): TokenData
    suspend fun validateRegisterData(data: RegisterValidationData): String
    suspend fun otpRegisterUser(user: OtpUserCreate): TokenData
}

