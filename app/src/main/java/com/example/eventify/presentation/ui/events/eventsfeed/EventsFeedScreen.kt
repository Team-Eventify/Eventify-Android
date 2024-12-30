package com.example.eventify.presentation.ui.events.eventsfeed

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import coil3.ImageLoader
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.events.eventsfeed.components.EventCard
import com.example.eventify.presentation.ui.events.eventsfeed.components.EventsFeedLazyColumn
import com.example.eventify.presentation.ui.events.eventsfeed.components.LoadingEventCard
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.util.UUID
import kotlin.random.Random

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
                            start = Random.nextInt(),
                            end = Random.nextInt()
                        )
                    },
                    isLoading = false
                ),
                actions = EventsFeedActions.default(),
                imageLoader = ImageLoader(LocalContext.current)
            )
        }
    }
}

@Preview(name = "EventsFeed Loading Dark", showBackground = true)
@Composable
private fun EventsFeedScreenDarkLoadingPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            EventsFeedScreen(
                state = EventsFeedState(
                    events = emptyList(),
                    isLoading = true
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
                            start = Random.nextInt(),
                            end = Random.nextInt()
                        )
                    },
                    isLoading = false
                ),
                actions = EventsFeedActions.default(),
                imageLoader = ImageLoader(LocalContext.current)

            )
        }
    }
}

@Preview(name = "EventsFeed Loading Light", showBackground = true)
@Composable
private fun EventsFeedScreenLightLoadingPreview() {
    EventifyTheme(darkTheme = false) {
        Surface {
            EventsFeedScreen(
                state = EventsFeedState(
                    events = emptyList(),
                    isLoading = true
                ),
                actions = EventsFeedActions.default(),
                imageLoader = ImageLoader(LocalContext.current)
            )
        }
    }
}



