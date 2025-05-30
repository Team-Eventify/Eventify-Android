package data.repositories.organizations

import data.models.Organization


interface OrganizationsRepository {
    suspend fun getOrganization(organizationId: String): Organization
}