package com.example.eventify.data.remote.api

import com.example.eventify.data.remote.models.users.ChangeUserRequest
import com.example.eventify.data.remote.utils.AuthRequired
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path


typealias CategorySlug = String

@AuthRequired
interface UsersAPI {

    @PATCH("{id}")
    suspend fun changeUser(@Path("id") userId: Int, @Body user: ChangeUserRequest)

    @GET("{id}")
    suspend fun getUserInfo(@Path("id") userId: Int)

    @GET("{id}")
    suspend fun getUserCategories(@Path("id") userId: Int)

    @PUT("{id}")
    suspend fun setUserCategories(@Path("id") userId: Int, categories: List<CategorySlug>)

}