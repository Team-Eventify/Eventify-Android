package feature.search.impl.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import domain.models.ShortEventItem
import feature.search.impl.state.EventId
import androidx.compose.foundation.lazy.items
import core.common.extentions.DateTimePattern
import core.common.extentions.durationUtcFormatted
import core.common.extentions.toUtcFormat
import uikit.components.cards.EventCard
import uikit.space18


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
                title = event.title,
                description = event.description,
                duration = durationUtcFormatted(event.start, event.end),
                location = event.location,
                startTime = event.start.toUtcFormat(DateTimePattern.ShortNamedDate),
                coverId = event.cover,
            ) {
                onEventClick(event.id)
            }
            Spacer(Modifier.height(space18))
        }
    }
}
