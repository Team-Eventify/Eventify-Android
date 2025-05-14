package data.remote.models.events


data class EventDetailResponse(
    val capacity: Int,
    val cover: String,
    val description: String,
    val end: Long,
    val id: String,
    val location: String,
    val organizationID: String,
    val pictures: List<String>,
    val categories: List<String>? = null,
    val start: Long,
    val state: String,
    val subscribed: Boolean,
    val title: String
)

