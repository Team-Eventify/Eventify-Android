package domain.extentions

import data.models.EventState

fun EventState.isSubscribeEnabled(): Boolean = this in listOf(EventState.PUBLISHED, EventState.CREATED)

fun EventState.isHidden(): Boolean = this in listOf(EventState.UNKNOWN, EventState.ARCHIEVED, EventState.UNDEFINED)

/**
 * Parse [String] to [EventState]. Returns [EventState.UNKNOWN] if the corresponding value not found.
 **/
fun String.toEventState(): EventState {
    if (!EventState.entries.map { it.name }.contains(this)) return EventState.UNKNOWN
    return EventState.valueOf(this)
}