package com.example.eventify.data.remote.api

import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.data.remote.models.auth.LogInResponse
import com.example.eventify.data.remote.models.auth.PublicKeyInfoResponse
import com.example.eventify.data.remote.models.auth.RefreshTokenRequestData
import com.example.eventify.data.remote.models.auth.RefreshTokenResponse
import com.example.eventify.data.remote.models.auth.RegisterResponse
import com.example.eventify.data.remote.models.auth.RegisterUserRequestData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthAPI {

    @POST(".")
    suspend fun refreshAccessToken(@Body data: RefreshTokenRequestData): Response<RefreshTokenResponse>

    @POST("register")
    suspend fun registerUser(@Body user: RegisterUserRequestData): Response<RegisterResponse>

    @POST("login")
    suspend fun logInUser(@Body payload: LogInRequestData): Response<LogInResponse>

    @GET("public")
    suspend fun getPublicKey(): Response<PublicKeyInfoResponse>


}