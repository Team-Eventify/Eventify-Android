package com.example.eventify.data.repositories.organizations

import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.Organization

interface OrganizationsRepository {
    suspend fun getOrganization(organizationId: String): Result<Organization, DataError.Network>
}