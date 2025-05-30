package data.remote.api

import data.remote.models.category.CategoryInfoResponse
import data.remote.utils.AuthRequired
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

@AuthRequired
internal interface CategoryAPI {

    @GET(".")
    suspend fun getCategoriesList(): Response<List<CategoryInfoResponse>>

    @GET("{id}")
    suspend fun readCategory(@Path("id") categoryId: String): Response<CategoryInfoResponse>

    @POST(".")
    suspend fun createCategory()
}