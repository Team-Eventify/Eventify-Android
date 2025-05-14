package data.remote.api

import data.remote.models.auth.LogInRequestData
import data.remote.models.auth.LogInResponse
import data.remote.models.auth.OtpRegisterUserRequest
import data.remote.models.auth.PublicKeyInfoResponse
import data.remote.models.auth.RefreshTokenRequestData
import data.remote.models.auth.RefreshTokenResponse
import data.remote.models.auth.RegisterResponse
import data.remote.models.auth.RegisterUserRequestData
import data.remote.models.auth.RegisterValidationRequest
import data.remote.models.auth.RegisterValidationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface AuthAPI {

    @POST(".")
    suspend fun refreshAccessToken(@Body data: RefreshTokenRequestData): Response<RefreshTokenResponse>

    @POST("register")
    suspend fun registerUser(@Body user: RegisterUserRequestData): Response<RegisterResponse>

    @POST("login")
    suspend fun logInUser(@Body payload: LogInRequestData): Response<LogInResponse>

    @GET("public")
    suspend fun getPublicKey(): Response<PublicKeyInfoResponse>

    @POST("registration/{validId}")
    suspend fun otpRegisterUser(@Path("validId") validationId: String, @Body user: OtpRegisterUserRequest): Response<RegisterResponse>

    @POST("validation")
    suspend fun validateRegisterData(@Body data: RegisterValidationRequest): Response<RegisterValidationResponse>
}