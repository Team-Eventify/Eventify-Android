package com.example.eventify.data.repositories.auth

import com.example.eventify.data.errors.UserNotFoundException
import com.example.eventify.data.models.TokenData
import com.example.eventify.data.models.UserCreate
import com.example.eventify.data.models.UserCredentials
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.data.remote.models.auth.RefreshTokenRequestData
import com.example.eventify.data.remote.models.auth.RegisterUserRequestData
import javax.inject.Inject

class AuthUserRepositoryImpl @Inject constructor (
    private val dataSource: AuthAPI
): AuthUserRepository {
    override suspend fun refreshAccessToken(refreshToken: String): TokenData {
        val response = dataSource.refreshAccessToken(
            data =  RefreshTokenRequestData(
                refresh = refreshToken
            )
        )
        val tokenData = when (response.code()) {
            200 -> response.body()?.let {
                TokenData(
                    accessToken = it.accessToken,
                    refreshToken = it.refreshToken,
                    userID = it.userID
                )
            }
            else -> null
        }
        return tokenData ?: throw Exception("Ошибка сервера.")
    }

    override suspend fun registerUser(user: UserCreate): TokenData {
        val response = dataSource.registerUser(
            user = RegisterUserRequestData(
                email = user.email,
                password = user.password
            )
        )

        val tokenData = when (response.code()) {
            200 -> response.body()?.let {
                TokenData(
                    accessToken = it.accessToken,
                    refreshToken = it.refreshToken,
                    userID = it.userID
                )
            }
            else -> null
        }

        return tokenData ?: error("Ошибка")
    }
    override suspend fun logInUser(credentials: UserCredentials): TokenData {
        val response = dataSource.logInUser(
            payload = LogInRequestData(
                email = credentials.login,
                password = credentials.password
            )
        )
        val tokenData = when (response.code()) {
            200 -> response.body()?.let {
                TokenData(
                    accessToken = it.accessToken,
                    refreshToken = it.refreshToken,
                    userID = it.userID
                )
            }
            404 -> throw UserNotFoundException("Пользователь не найден.")
            else -> null
        }

        return tokenData ?: throw UserNotFoundException("Пользователь не найден.")
    }

}