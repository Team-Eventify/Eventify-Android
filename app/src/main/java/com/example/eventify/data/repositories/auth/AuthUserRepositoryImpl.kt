package com.example.eventify.data.repositories.auth

import com.example.eventify.domain.models.TokenData
import com.example.eventify.domain.models.UserCreate
import com.example.eventify.domain.models.UserCredentials
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.data.remote.models.auth.RefreshTokenRequestData
import com.example.eventify.data.remote.models.auth.RegisterUserRequestData
import com.example.eventify.data.remote.utils.handle
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import timber.log.Timber
import javax.inject.Inject

class AuthUserRepositoryImpl @Inject constructor (
    private val dataSource: AuthAPI
): AuthUserRepository {
    override suspend fun refreshAccessToken(refreshToken: String): Result<TokenData, DataError> = try {
        dataSource.refreshAccessToken(
            data =  RefreshTokenRequestData(
                refresh = refreshToken
            )
        ).handle{
            TokenData(
                refreshToken = it.refreshToken,
                accessToken = it.accessToken,
                userID = it.userID
            )
        }

    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun registerUser(user: UserCreate): Result<TokenData, DataError> = try {
        dataSource.registerUser(
            user = RegisterUserRequestData(
                email = user.email,
                password = user.password
            )
        ).handle{
            TokenData(
                refreshToken = it.refreshToken,
                accessToken = it.accessToken,
                userID = it.userID
            )
        }

    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }


    override suspend fun logInUser(credentials: UserCredentials): Result<TokenData, DataError> = try {
        dataSource.logInUser(
            payload = LogInRequestData(
                email = credentials.login,
                password = credentials.password
            )
        ).handle{
            TokenData(
                refreshToken = it.refreshToken,
                accessToken = it.accessToken,
                userID = it.userID
            )
        }

    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

}