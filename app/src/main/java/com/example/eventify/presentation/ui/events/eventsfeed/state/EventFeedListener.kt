package com.example.eventify.presentation.ui.events.eventsfeed.state

interface EventFeedListener {
    fun onEventClick(eventId: String): Unit
    fun onRefreshData(): Unit
}