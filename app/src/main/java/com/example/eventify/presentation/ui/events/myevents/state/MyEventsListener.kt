package com.example.eventify.presentation.ui.events.myevents.state

interface MyEventsListener {
    fun onRefresh(): Unit
    fun navigateToEvent(eventId: String): Unit
    fun navigateToFeedback(eventId: String): Unit
    fun navigateToFeed(): Unit
}