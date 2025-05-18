package data.remote.models.events


internal data class EventInfoResponse(
    val id: String,
    val capacity: Int,
    val description: String,
    val end: Long,
    val start: Long,
    val state: String,
    val title: String,
    val cover: String,
    val location: String,
    val subscribed: Boolean,
    val categories: List<String>? = null,
    val organizationID: String,
    val pictures: List<String>,
)

