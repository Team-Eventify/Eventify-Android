package com.example.eventify.presentation.ui.events.eventdetail.state

import com.example.eventify.domain.models.FullEventDetail

interface EventDetailListener {
    fun navigateUp(): Unit
    fun onActionClick(): Unit
    fun goToRatePage(): Unit
}