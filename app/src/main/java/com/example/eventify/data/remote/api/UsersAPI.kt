package com.example.eventify.data.remote.api

import com.example.eventify.data.models.CategoryInfo
import com.example.eventify.data.remote.models.category.CategoryInfoResponse
import com.example.eventify.data.remote.models.events.EventInfoResponse
import com.example.eventify.data.remote.models.users.ChangeUserRequest
import com.example.eventify.data.remote.models.users.UserInfoResponse
import com.example.eventify.data.remote.utils.AuthRequired
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path


typealias CategorySlug = String

@AuthRequired
interface UsersAPI {

    @PATCH("{id}")
    suspend fun changeUser(@Path("id") userId: String, @Body user: ChangeUserRequest): Response<UserInfoResponse>

    @GET("{id}")
    suspend fun getUserInfo(@Path("id") userId: String): Response<UserInfoResponse>

    @GET("{id}/categories")
    suspend fun getUserCategories(@Path("id") userId: String): Response<List<CategoryInfoResponse>>

    @PUT("{id}/categories")
    suspend fun setUserCategories(@Path("id") userId: String, @Body categories: List<CategorySlug>): Response<Unit>

    @GET("{id}/events")
    suspend fun getUserSubscribedEvents(@Path("id") userId: String): Response<List<EventInfoResponse>>
    @DELETE("{id}")
    suspend fun deleteUserById(@Path("id") userId: String): Response<Unit>

}