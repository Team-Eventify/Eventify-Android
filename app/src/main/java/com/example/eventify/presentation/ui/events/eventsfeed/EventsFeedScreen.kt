package com.example.eventify.presentation.ui.events.eventsfeed

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import com.example.eventify.R
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.events.eventsfeed.components.EventCard
import com.example.eventify.presentation.ui.shared.HeadingText
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
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 15.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                HeadingText(stringResource(R.string.popular_events))
            }
            items(
                state.events,
                key = { it.id }
            ){ event ->
                EventCard(
                    event = event,
                    onClick = {
                        actions.onEventClick(event.id)
                    },
                    imageLoader = imageLoader,
                    modifier = Modifier
                        .animateContentSize()

                )
                HorizontalDivider()
            }
        }
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

