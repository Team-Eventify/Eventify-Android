package com.example.eventify.data.remote.api

import androidx.compose.ui.geometry.Offset
import com.example.eventify.data.remote.models.events.CreateEventRequest
import com.example.eventify.data.remote.models.events.EventInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface EventsAPI {

    @GET(".")
    suspend fun getEventsList(@Query("offset") offset: Int? = null, @Query("limit") limit: Int? = null): Response<List<EventInfoResponse>>

    @GET("{id}")
    suspend fun getEvent(@Path("id") eventId: Int): EventInfoResponse

    @POST(".")
    suspend fun createNewEvent(event: CreateEventRequest)

}