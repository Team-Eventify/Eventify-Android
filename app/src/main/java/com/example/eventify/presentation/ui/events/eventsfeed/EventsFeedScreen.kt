package com.example.eventify.presentation.ui.events.eventsfeed

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.ImageLoader
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.events.eventsfeed.components.EventCard
import com.example.eventify.presentation.ui.events.eventsfeed.components.EventsFeedLazyColumn
import com.example.eventify.presentation.ui.events.eventsfeed.components.LoadingEventCard
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun EventsFeedScreen(
    state: EventsFeedState,
    actions: EventsFeedActions,
    imageLoader: ImageLoader
) {
    val swipeRefreshState = rememberSwipeRefreshState(state.isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = actions.onRefreshData
    ) {
        EventsFeedLazyColumn(
            events = state.events,
            isLoading = state.isLoading,
            loadingEventItem = {
                LoadingEventCard()
            },
            eventItem = { event ->
                EventCard(
                    event = event,
                    onClick = {
                        actions.onEventClick(event.id)
                    },
                    imageLoader = imageLoader
                )
            }
        )
    }
}

@Composable
@Preview(name = "EventsFeed Default Dark", showBackground = true)
private fun EventsFeedScreenDefaultDarkPreview() {
    EventifyTheme(
        darkTheme = true
    ) {
        Surface {
            EventsFeedScreen(
                state = EventsFeedState(
                    events = listOf(
                        ShortEventItem(
                            id = "",
                            title = "День открытых дверей НИТУ МИСИС",
                            description = "Дни открытых дверей — это уникальная возможность для старшеклассников больше узнать о специальностях, которым обучают в Унивеситете МИСИС.",
                            start = 312313123,
                            end = 231121243
                        )
                    ),
                ),
                actions = EventsFeedActions.default(),
                imageLoader = ImageLoader(LocalContext.current)
            )
        }
    }
}

@Composable
@Preview(name = "EventsFeed Default Light", showBackground = true)
private fun EventsFeedScreenDefaultLightPreview() {
    EventifyTheme(
        darkTheme = false
    ) {
        Surface {
            EventsFeedScreen(
                state = EventsFeedState(
                    events = listOf(
                        ShortEventItem(
                            id = "",
                            title = "День открытых дверей НИТУ МИСИС",
                            description = "Дни открытых дверей — это уникальная возможность для старшеклассников больше узнать о специальностях, которым обучают в Унивеситете МИСИС.",
                            start = 312313123,
                            end = 231121243
                        )
                    ),
                ),
                actions = EventsFeedActions.default(),
                imageLoader = ImageLoader(LocalContext.current)

            )
        }
    }
}

