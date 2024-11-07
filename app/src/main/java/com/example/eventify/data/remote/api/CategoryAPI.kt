package com.example.eventify.data.remote.api

import com.example.eventify.data.remote.utils.AuthRequired
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

@AuthRequired
interface CategoryAPI {

    @GET("/category")
    suspend fun getCategoriesList()

    @GET("/category/{id}")
    suspend fun readCategory(@Path("id") categoryId: Int)

    @POST("/category")
    suspend fun createCategory()
}