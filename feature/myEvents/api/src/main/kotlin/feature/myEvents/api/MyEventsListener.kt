package feature.myEvents.api

interface MyEventsListener {
    fun onRefresh(): Unit
    fun navigateToEvent(eventId: String): Unit
    fun navigateToFeedback(eventId: String): Unit
    fun navigateToFeed(): Unit
}