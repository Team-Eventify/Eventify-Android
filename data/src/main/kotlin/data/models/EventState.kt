package data.models

enum class EventState {
    CREATED,
    CANCELED,
    FINISHED,
    UNDEFINED,
    ARCHIEVED,
    PUBLISHED,
    UNKNOWN,
}

fun String.toEventState(): EventState = try {
        EventState.valueOf(this)
    } catch (e: IllegalArgumentException) {
        EventState.UNKNOWN
    }

