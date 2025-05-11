package data.repositories.organizations

import data.models.Organization
import data.remote.api.OrganizationsAPI
import data.remote.models.organizations.toBusiness
import data.remote.utils.handle
import javax.inject.Inject


class OrganizationsRepositoryImpl @Inject constructor(
    private val dataSource: OrganizationsAPI
) : OrganizationsRepository{
    override suspend fun getOrganization(organizationId: String): Organization {
        return dataSource.getOrganization(organizationId).handle {
            transformSuccess { it.toBusiness() }
        }
    }
}