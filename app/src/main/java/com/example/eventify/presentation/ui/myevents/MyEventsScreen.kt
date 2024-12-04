package com.example.eventify.presentation.ui.myevents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (state.upComingEvents.isNotEmpty()) {
                HeadingText(text = "Предстоящие мероприятия")
            }
            Spacer(modifier = Modifier.height(20.dp))
            state.upComingEvents.forEach { event ->
                UpComingEventCard(event = event)
                Spacer(modifier = Modifier.height(10.dp))
            }

            if (state.finishedEvents.isNotEmpty()){
                HeadingText(text = "Прошедшие мероприятия")
            }
            Spacer(modifier = Modifier.height(20.dp))
            state.finishedEvents.forEach { event ->
                FinishedEventCard(event = event)
                Spacer(modifier = Modifier.height(10.dp))
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

