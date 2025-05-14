package data.remote.api

import data.remote.models.events.EventDetailResponse
import data.remote.models.events.EventInfoResponse
import data.remote.utils.AuthRequired
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

@AuthRequired
interface EventsAPI {

    @GET(".")
    suspend fun getEventsList(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("ownerID") ownerID: String? = null,
        @Query("start") start: Int? = null,
        @Query("end") end: Int? = null,
        @Query("categoryIDs") categoryIds: String? = null,
        @Query("searchByTitle") title: String? = null,
    ): Response<List<EventInfoResponse>>

    @GET("{id}")
    suspend fun getEvent(@Path("id") eventId: String): Response<EventDetailResponse>

    @POST("{eventId}/subscribers")
    suspend fun subscribeForEvent(@Path("eventId") eventId: String): Response<Unit>

    @DELETE("{eventId}/subscribers/{userId}")
    suspend fun unsubscribeForEvent(@Path("eventId") eventId: String, @Path("userId") userId: String): Response<Unit>

}