package com.example.eventify.data.remote.api

import com.example.eventify.data.remote.models.category.CategoryInfoResponse
import com.example.eventify.data.remote.utils.AuthRequired
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

@AuthRequired
interface CategoryAPI {

    @GET(".")
    suspend fun getCategoriesList(): Response<List<CategoryInfoResponse>>

    @GET("{id}")
    suspend fun readCategory(@Path("id") categoryId: String): Response<CategoryInfoResponse>

    @POST(".")
    suspend fun createCategory()
}