package com.example.eventify.presentation.ui.eventdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.eventify.data.models.EventInfo


/**
 * UI State that represents EventDetailScreen
 **/
data class EventDetailState(
    val event: EventInfo? = null
)

/**
 * EventDetail Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class EventDetailActions(
    val navigateUp: () -> Unit,
)