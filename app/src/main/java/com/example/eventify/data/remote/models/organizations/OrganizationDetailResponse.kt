package com.example.eventify.data.remote.models.organizations

import com.example.eventify.domain.models.Organization

data class OrganizationDetailResponse(
    val description: String,
    val id: String,
    val photoID: String, // TODO вставлять айдишник в пкть к файлу
    val title: String,
)


fun OrganizationDetailResponse.toBusiness() = Organization(
    id = id,
    description = description,
    photoUrl = "https://eventify.website/api/v1/files/$photoID",
    title = title
)