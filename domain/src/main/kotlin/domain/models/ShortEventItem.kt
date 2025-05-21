package domain.models

import core.common.extentions.asDate
import core.common.extentions.toLocalDateTime
import data.models.Event
import java.time.Duration
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
){
    val duration: String
        get() {
            val startTime = start.toLocalDateTime()
            val endTime = end.toLocalDateTime()

            val duration = Duration.between(startTime, endTime)

            if (duration.toDays() <= 1)
                return startTime.asDate()

            return listOf(startTime.asDate(), endTime.asDate()).joinToString(" - ")
        }
}

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