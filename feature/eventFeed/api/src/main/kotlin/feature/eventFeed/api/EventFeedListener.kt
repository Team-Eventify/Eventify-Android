package feature.eventFeed.api

interface EventFeedListener {
    fun onEventClick(eventId: String): Unit
    fun onRefreshData(): Unit
}