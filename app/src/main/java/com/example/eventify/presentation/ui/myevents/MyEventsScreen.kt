package com.example.eventify.presentation.ui.myevents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.shared.HeadingText
import com.example.eventify.presentation.ui.myevents.components.FinishedEventCard
import com.example.eventify.presentation.ui.myevents.components.UpComingEventCard
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MyEventsScreen(
    state: MyEventsState,
    actions: MyEventsActions,
) {
    val swipeRefreshState = rememberSwipeRefreshState(state.isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = actions.onRefresh
    ){

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                if (state.upComingEvents.isNotEmpty())
                    HeadingText(text = "Предстоящие мероприятия")
            }
            items(state.upComingEvents) { event ->
                UpComingEventCard(event = event)
            }


            item {
                if (state.finishedEvents.isNotEmpty())
                    Spacer(modifier = Modifier.height(10.dp))
                    HeadingText(text = "Прошедшие мероприятия")
            }
            items(state.finishedEvents) { event ->
                FinishedEventCard(event = event)
            }
        }

    }

}



@Composable
@Preview(name = "MyEvents Default Dark", showBackground = true)
private fun MyEventsScreenDefaultDarkPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            MyEventsScreen(
                state = MyEventsState(
                    upComingEvents = listOf(
                        ShortEventItem(
                            id = "",
                            title = "День первокурсника",
                            description = "",
                            start = 1231313,
                            end = 231231312
                        ),
                        ShortEventItem(
                            id = "",
                            title = "День первокурсника",
                            description = "",
                            start = 1231313,
                            end = 231231312
                        ),
                        ShortEventItem(
                            id = "",
                            title = "День первокурсника",
                            description = "",
                            start = 1231313,
                            end = 231231312
                        )
                    ),
                    finishedEvents = listOf(
                        ShortEventItem(
                            id = "",
                            title = "День первокурсника",
                            description = "",
                            start = 1231313,
                            end = 231231312
                        ),
                        ShortEventItem(
                            id = "",
                            title = "День первокурсника",
                            description = "",
                            start = 1231313,
                            end = 231231312
                        ),
                        ShortEventItem(
                            id = "",
                            title = "День первокурсника",
                            description = "",
                            start = 1231313,
                            end = 231231312
                        )
                    )
                ),
                actions = MyEventsActions(
                    onRefresh = {}
                )
            )
        }
    }
}

@Composable
@Preview(name = "MyEvents Default Light", showBackground = true)
private fun MyEventsScreenDefaultLightPreview() {
    EventifyTheme(darkTheme = false) {
        Surface {
            MyEventsScreen(
                state = MyEventsState(
                    upComingEvents = listOf(
                        ShortEventItem(
                            id = "",
                            title = "День первокурсника",
                            description = "",
                            start = 1231313,
                            end = 231231312
                        ),
                        ShortEventItem(
                            id = "",
                            title = "День первокурсника",
                            description = "",
                            start = 1231313,
                            end = 231231312
                        ),
                        ShortEventItem(
                            id = "",
                            title = "День первокурсника",
                            description = "",
                            start = 1231313,
                            end = 231231312
                        )
                    ),
                    finishedEvents = listOf(
                        ShortEventItem(
                            id = "",
                            title = "День первокурсника",
                            description = "",
                            start = 1231313,
                            end = 231231312
                        ),
                        ShortEventItem(
                            id = "",
                            title = "День первокурсника",
                            description = "",
                            start = 1231313,
                            end = 231231312
                        ),
                        ShortEventItem(
                            id = "",
                            title = "День первокурсника",
                            description = "",
                            start = 1231313,
                            end = 231231312
                        )
                    )
                ),
                actions = MyEventsActions(
                    onRefresh = {}
                )
            )
        }
    }
}

