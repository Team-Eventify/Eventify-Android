package com.example.eventify.data.remote.api

import com.example.eventify.data.remote.models.organizations.OrganizationDetailResponse
import com.example.eventify.data.remote.utils.AuthRequired
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


@AuthRequired
interface OrganizationsAPI {
    @GET("organizations/{id}")
    suspend fun getOrganization(@Path("id") organizationId: String): Response<OrganizationDetailResponse>
}
