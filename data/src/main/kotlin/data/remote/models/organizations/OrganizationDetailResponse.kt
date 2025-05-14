package data.remote.models.organizations

data class OrganizationDetailResponse(
    val description: String,
    val id: String,
    val photoID: String, // TODO вставлять айдишник в пкть к файлу
    val title: String,
)


