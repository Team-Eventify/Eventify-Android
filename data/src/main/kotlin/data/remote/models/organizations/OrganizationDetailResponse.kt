package data.remote .models.organizations

import android.provider.ContactsContract

data class OrganizationDetailResponse(
    val description: String,
    val id: String,
    val photoID: String, // TODO вставлять айдишник в пкть к файлу
    val title: String,
)


fun OrganizationDetailResponse.toBusiness() = ContactsContract.CommonDataKinds.Organization(
    id = id,
    description = description,
    photoUrl = "https://eventify.website/api/v1/files/$photoID",
    title = title
)