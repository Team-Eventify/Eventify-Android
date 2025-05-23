package feature.myEvents.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import domain.models.ShortEventItem
import feature.myEvents.api.MyEventsListener
import feature.myEvents.impl.components.FinishedEventCard
import feature.myEvents.impl.components.UpComingEventCard
import feature.myEvents.impl.state.UiState
import uikit.EventifyTheme
import uikit.LocalDimentions
import uikit.components.HeadingText
import java.util.UUID
import kotlin.collections.isNotEmpty
import data.models.EventState
import com.example.eventify.uikit.R as UiKitR


@Composable
fun MyEventsScreen(
    state: UiState.ShowMyEvents,
    actions: MyEventsListener,
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)
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
                    HeadingText(text = stringResource(UiKitR.string.upcoming_events))
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
                    HeadingText(text = stringResource(UiKitR.string.finished_events))
                }
            }
            // TODO сделать одну карточку для всех состоянией и менять наложение tint
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
