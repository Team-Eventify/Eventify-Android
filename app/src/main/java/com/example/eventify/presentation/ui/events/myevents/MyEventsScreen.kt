package com.example.eventify.presentation.ui.events.myevents

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.events.myevents.components.FinishedEventCard
import com.example.eventify.presentation.ui.events.myevents.components.UpComingEventCard
import com.example.eventify.presentation.ui.common.HeadingText
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.LocalDimentions
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.util.UUID


@Composable
fun MyEventsScreen(
    state: UiState.ShowMyEvents,
    actions: MyEventsActions,
) {
    val swipeRefreshState = rememberSwipeRefreshState(state.isRefreshing)
    val dimmentions = LocalDimentions.current

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = actions.onRefresh
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                if (state.upComingEvents.isNotEmpty())
                    HeadingText(text = stringResource(R.string.upcoming_events))
            }
            items(state.upComingEvents) { event ->
                UpComingEventCard(
                    event = event,
                    onClick = actions.navigateToEvent
                )
            }


            item {
                if (state.finishedEvents.isNotEmpty()){
                    Spacer(modifier = Modifier.height(10.dp))
                    HeadingText(text = stringResource(R.string.finished_events))
                }
            }
            items(state.finishedEvents) { event ->
                FinishedEventCard(
                    event = event,
                    onClick = actions.navigateToEvent,
                    showFeedbackButton = false,
                    onFeedbackAction = actions.navigateToFeedback
                )
            }
        }

    }

}



@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun MyEventsScreenDefaultDarkPreview() {
    EventifyTheme {
        Surface {
            MyEventsScreen(
                state = UiState.ShowMyEvents(
                    upComingEvents = List(3) {
                        ShortEventItem(
                            id = UUID.randomUUID().toString(),
                            title = LoremIpsum(3).values.joinToString(),
                            description = LoremIpsum(3).values.joinToString(),
                            start = 1231313,
                            end = 231231312,
                            location = LoremIpsum(2).values.joinToString()
                        )
                    },
                    finishedEvents = List(3) {
                        ShortEventItem(
                            id = UUID.randomUUID().toString(),
                            title = LoremIpsum(3).values.joinToString(),
                            description = LoremIpsum(3).values.joinToString(),
                            start = 1231313,
                            end = 231231312,
                            location = LoremIpsum(2).values.joinToString()
                        )
                    }
                ),
                actions = MyEventsActions(
                    onRefresh = {},
                    navigateToEvent = {}
                )
            )
        }
    }
}
