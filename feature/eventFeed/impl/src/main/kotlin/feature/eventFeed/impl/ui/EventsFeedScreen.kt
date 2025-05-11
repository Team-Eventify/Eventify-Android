package feature.eventFeed.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import feature.eventFeed.api.EventFeedListener
import feature.eventFeed.impl.components.EventCard
import feature.eventFeed.impl.state.UiState
import uikit.EventifyTheme
import uikit.LocalDimentions
import java.util.UUID
import kotlin.random.Random

@Composable
internal fun EventsFeedScreen(
    state: UiState.ShowFeed,
    actions: EventFeedListener,
) {
    val swipeRefreshState = rememberSwipeRefreshState(state.isRefreshing)
    val dimmentions = LocalDimentions.current

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = actions::onRefreshData
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = dimmentions.windowPaddings,
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                HeadingText(stringResource(R.string.popular_events))
            }
            items(
                items = state.events,
                key = { it.id }
            ){ event ->
                EventCard(
                    event = event,
                    onClick = actions::onEventClick,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun EventsFeedScreenDefaultDarkPreview() {
    EventifyTheme {
        Surface {
            EventsFeedScreen(
                state = UiState.ShowFeed(
                    events = List(3){
                        ShortEventItem(
                            id = UUID.randomUUID().toString(),
                            title = LoremIpsum(3)
                                .values
                                .toList()
                                .joinToString { it },
                            description = LoremIpsum(20)
                                .values
                                .toList()
                                .joinToString { it },
                            cover = "",
                            start = Random.nextLong(),
                            state = EventState.PUBLISHED,
                            end = Random.nextLong(),
                            location = LoremIpsum(15)
                                .values
                                .joinToString(),
                        )
                    },
                ),
                actions = object : EventFeedListener {
                    override fun onEventClick(eventId: String) = Unit
                    override fun onRefreshData() = Unit
                }
            )
        }
    }
}
