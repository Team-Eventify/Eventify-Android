package com.example.eventify.data.repositories.organizations

import com.example.eventify.data.remote.api.OrganizationsAPI
import com.example.eventify.data.remote.models.organizations.toBusiness
import com.example.eventify.data.remote.utils.handle
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.Organization
import javax.inject.Inject


class OrganizationsRepositoryImpl @Inject constructor(
    private val dataSource: OrganizationsAPI
) : OrganizationsRepository{
    override suspend fun getOrganization(organizationId: String): Result<Organization, DataError.Network> = try {
        dataSource.getOrganization(organizationId).handle {
            transformSuccess { it.toBusiness() }
        }
    } catch (e: Exception) {
        Result.Error(DataError.Network.UNKNOWN)
    }
}