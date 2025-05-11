package feature.myEvents.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import feature.myEvents.api.MyEventsListener
import feature.myEvents.impl.components.FinishedEventCard
import feature.myEvents.impl.components.UpComingEventCard
import feature.myEvents.impl.state.UiState
import uikit.LocalDimentions
import java.util.UUID
import kotlin.collections.isNotEmpty


@Composable
fun MyEventsScreen(
    state: UiState.ShowMyEvents,
    actions: MyEventsListener,
) {
    val swipeRefreshState = rememberSwipeRefreshState(state.isRefreshing)
    val dimmentions = LocalDimentions.current

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = actions::onRefresh
    ) {
        LazyColumn(
            contentPadding = dimmentions.windowPaddings,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                if (state.upComingEvents.isNotEmpty())
                    HeadingText(text = stringResource(R.string.upcoming_events))
            }
            items(state.upComingEvents) { event ->
                UpComingEventCard(
                    event = event,
                    onClick = actions::navigateToEvent
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
                    onClick = actions::navigateToEvent,
                    showFeedbackButton = false,
                    onFeedbackAction = actions::navigateToFeedback
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
                            location = LoremIpsum(2).values.joinToString(),
                            state = EventState.PUBLISHED,
                        )
                    },
                    finishedEvents = List(3) {
                        ShortEventItem(
                            id = UUID.randomUUID().toString(),
                            title = LoremIpsum(3).values.joinToString(),
                            description = LoremIpsum(3).values.joinToString(),
                            start = 1231313,
                            end = 231231312,
                            location = LoremIpsum(2).values.joinToString(),
                            state = EventState.FINISHED,
                        )
                    }
                ),
                actions = object : MyEventsListener {
                    override fun onRefresh() = Unit
                    override fun navigateToEvent(eventId: String) = Unit
                    override fun navigateToFeedback(eventId: String) = Unit
                    override fun navigateToFeed() = Unit
                }
            )
        }
    }
}
