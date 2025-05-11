package data.repositories.auth

import data.models.OtpUserCreate
import data.models.RegisterValidationData
import data.models.TokenData
import data.models.UserCreate
import data.models.UserCredentials
import data.remote.api.AuthAPI
import data.remote.models.auth.LogInRequestData
import data.remote.models.auth.OtpRegisterUserRequest
import data.remote.models.auth.RefreshTokenRequestData
import data.remote.models.auth.RegisterUserRequestData
import data.remote.models.auth.RegisterValidationRequest
import data.remote.utils.handle
import javax.inject.Inject


class AuthUserRepositoryImpl @Inject constructor (
    private val dataSource: AuthAPI
): AuthUserRepository {
    override suspend fun refreshAccessToken(refreshToken: String): TokenData {
        return dataSource.refreshAccessToken(
            data = RefreshTokenRequestData(
                refresh = refreshToken
            )
        ).handle{
            transformSuccess {
                TokenData(
                    refreshToken = it.refreshToken,
                    accessToken = it.accessToken,
                    userID = it.userID
                )
            }
        }

    }

    override suspend fun registerUser(user: UserCreate): TokenData {
        return dataSource.registerUser(
            user = RegisterUserRequestData(
                email = user.email,
                password = user.password
            )
        ).handle{
            transformSuccess {
                TokenData(
                    refreshToken = it.refreshToken,
                    accessToken = it.accessToken,
                    userID = it.userID
                )
            }
        }

    }

    override suspend fun logInUser(credentials: UserCredentials): TokenData {
        return dataSource.logInUser(
            payload = LogInRequestData(
                email = credentials.login,
                password = credentials.password
            )
        ).handle{
            transformSuccess {
                TokenData(
                    refreshToken = it.refreshToken,
                    accessToken = it.accessToken,
                    userID = it.userID
                )
            }
        }

    }

    override suspend fun validateRegisterData(data: RegisterValidationData): String {
        return dataSource.validateRegisterData(
            data = RegisterValidationRequest(
                email = data.email,
                password = data.password
            )
        ).handle {
            transformSuccess {
                it.validation_id
            }
        }
    }

    override suspend fun otpRegisterUser(user: OtpUserCreate): TokenData {
        return dataSource.otpRegisterUser(
            validationId = user.validationResultId,
            user = OtpRegisterUserRequest(
                email = user.email,
                password = user.password,
                code = user.code
            ),
        ).handle {
            transformSuccess {
                TokenData(
                    refreshToken = it.refreshToken,
                    accessToken = it.accessToken,
                    userID = it.userID
                )
            }
        }
    }
}