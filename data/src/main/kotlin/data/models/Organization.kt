package data.models

import data.remote.models.organizations.OrganizationDetailResponse

data class Organization(
    val description: String,
    val id: String,
    val photoUrl: String,
    val title: String,
)

internal fun OrganizationDetailResponse.toBusiness() = Organization(
    id = id,
    description = description,
    photoUrl = "https://eventify.website/api/v1/files/$photoID",
    title = title
)
