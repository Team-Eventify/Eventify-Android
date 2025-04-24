package com.example.eventify.presentation.ui.searchresult.state

import com.example.eventify.presentation.ui.events.search.state.EventId

interface SearchDetailListener {
    fun onBackClick()
    fun onEventClick(eventId: EventId)
}