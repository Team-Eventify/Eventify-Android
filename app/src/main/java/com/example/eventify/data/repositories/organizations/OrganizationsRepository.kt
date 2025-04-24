package com.example.eventify.data.repositories.organizations

import com.example.eventify.domain.models.Organization

interface OrganizationsRepository {
    suspend fun getOrganization(organizationId: String): Organization
}