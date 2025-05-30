package data.models

import data.remote.models.events.EventDetailResponse

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


// TODO refactor pictures
internal fun EventDetailResponse.toBusiness() = EventDetail(
    capacity = this.capacity ,
    cover = this.cover ,
    description = this.description ,
    end = this.end ,
    id = this.id ,
    location = this.location ,
    organizationID = this.organizationID ,
    pictures = this.pictures.map { "https://eventify.website/api/v1/files/$it" },
    start = this.start ,
    state = this.state.toEventState() ,
    subscribed = this.subscribed ,
    title = this.title,
    categories = this.categories
)

