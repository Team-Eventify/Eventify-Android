package com.example.eventify.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CategoryAPI {

    @GET("/category")
    suspend fun getCategoriesList(@Header("Authorization") authToken: String)

    @GET("/category/{id}")
    suspend fun readCategory(@Path("id") categoryId: Int, @Header("Authorization") authToken: String)

    @POST("/category")
    suspend fun createCategory(@Header("Authorization") authToken: String)
}