package data.remote.models.events

internal data class CreateEventRequest(
    val description: String,
    val end: Int,
    val ownerID: String,
    val start: Int,
    val title: String
)