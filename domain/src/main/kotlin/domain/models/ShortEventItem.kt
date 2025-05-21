package domain.models

import data.models.Event
import data.models.EventState


data class ShortEventItem(
    val id: String,
    val title: String,
    val description: String,
    val cover: String = "",
    val start: Long,
    val state: EventState,
    val end: Long,
    val location: String
)

fun Event.toShort() = ShortEventItem(
    id = this.id,
    title = this.title,
    description = this.description,
    cover = this.cover,
    start = this.start,
    state = this.state,
    end = this.end,
    location = this.location,
)