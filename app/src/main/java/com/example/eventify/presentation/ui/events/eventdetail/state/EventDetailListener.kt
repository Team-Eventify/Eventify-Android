package com.example.eventify.presentation.ui.events.eventdetail.state

import com.example.eventify.domain.models.FullEventDetail

interface EventDetailListener {
    fun navigateUp(): Unit
    fun onSubscribe(): Unit
    fun onUnsubscribe(): Unit
    fun goToRatePage(): Unit
}