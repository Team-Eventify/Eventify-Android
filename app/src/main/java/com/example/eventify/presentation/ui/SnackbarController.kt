package com.example.eventify.presentation.ui

import androidx.compose.material3.SnackbarDuration
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


data class SnackbarEvent(
    val message: String,
    val duration: SnackbarDuration = SnackbarDuration.Short,
    val action: SnackbarAction? = null,
    val withDismissAction: Boolean = true
)


data class SnackbarAction(
    val name: String,
    val action: () -> Unit
)


object SnackbarController {
    private val _events = Channel<SnackbarEvent>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: SnackbarEvent) {
        _events.send(event)
    }
}