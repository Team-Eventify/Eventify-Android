package com.example.eventify.presentation.ui.events.search.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.events.eventsfeed.components.EventCard
import com.example.eventify.presentation.ui.events.search.state.EventId
import com.example.eventify.presentation.ui.theme.space18

@Composable
fun EventsSearch(
    events: List<ShortEventItem>,
    onEventClick: (EventId) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn {
        items(
            events,
            key = { it.id }
        ) { event ->
            EventCard(
                event = event,
                onClick = onEventClick,
            )
            Spacer(Modifier.height(space18))
        }
    }
}
