package data.remote.api

import data.remote.models.organizations.OrganizationDetailResponse
import data.remote.utils.AuthRequired
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


@AuthRequired
internal interface OrganizationsAPI {
    @GET("organizations/{id}")
    suspend fun getOrganization(@Path("id") organizationId: String): Response<OrganizationDetailResponse>
}
