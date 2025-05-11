package data.models


data class Event(
    val id: String,
    val capacity: Int,
    val cover: String,
    val description: String,
    val end: Long,
    val organizationID: String,
    val start: Long,
    val state: EventState,
    val title: String,
    val location: String,
    val subscribed: Boolean,
    val categories: List<String>? = null
)

