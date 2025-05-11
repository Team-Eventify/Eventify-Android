package data.models

typealias PictureUUID = String
typealias CategoryUUID = String

data class EventDetail(
    val capacity: Int,
    val cover: String,
    val description: String,
    val end: Long,
    val id: String,
    val location: String,
    val organizationID: String,
    val pictures: List<PictureUUID>,
    val categories: List<CategoryUUID>? = null,
    val start: Long,
    val state: EventState,
    val subscribed: Boolean,
    val title: String
)

