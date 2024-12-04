package com.example.eventify.presentation.ui.eventsfeed

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.models.EventFeedResult
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.shared.CategoryCard
import com.example.eventify.presentation.ui.shared.EventCard
import com.example.eventify.presentation.ui.shared.HeadingText
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun EventsFeedScreen(
    state: EventsFeedState,
    actions: EventsFeedActions,
) {
    val swipeRefreshState = rememberSwipeRefreshState(state.result is EventFeedResult.Refreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = actions.onRefreshData
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HeadingText(stringResource(R.string.popular_events))

            state.events.forEach { event ->
                EventCard(
                    event = event,
                    onClick = {},
                    modifier = Modifier
                        .animateContentSize()
                        .clickable {
                            actions.onEventClick(event.id)
                        }
                )
                HorizontalDivider()
            }

            HeadingText(stringResource(R.string.categories_based_on_interests))

            state.categories.forEach { category ->
                CategoryCard(
                    category = category,
                    onClick = { categoryid -> }
                )
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
                    categories = listOf(

                    ),
                    events = listOf(
                        ShortEventItem(
                            id = "",
                            title = "День открытых дверей НИТУ МИСИС",
                            description = "Дни открытых дверей — это уникальная возможность для старшеклассников больше узнать о специальностях, которым обучают в Унивеситете МИСИС.",
                            start = 312313123,
                            end = 231121243
                        )
                    ),
                    result = EventFeedResult.Idle
                ),
                actions = EventsFeedActions.default()
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
                    categories = listOf(

                    ),
                    events = listOf(
                        ShortEventItem(
                            id = "",
                            title = "День открытых дверей НИТУ МИСИС",
                            description = "Дни открытых дверей — это уникальная возможность для старшеклассников больше узнать о специальностях, которым обучают в Унивеситете МИСИС.",
                            start = 312313123,
                            end = 231121243
                        )
                    ),
                    result = EventFeedResult.Idle
                ),
                actions = EventsFeedActions.default()
            )
        }
    }
}

