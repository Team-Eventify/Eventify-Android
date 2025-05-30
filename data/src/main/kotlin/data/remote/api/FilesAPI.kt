package data.remote.api

import data.remote.utils.AuthRequired
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


@AuthRequired
internal interface FilesAPI {

    @GET("{id}")
    suspend fun getFile(@Path("id") id: String): Response<ResponseBody>

    @POST(".")
    suspend fun uploadFile()

}