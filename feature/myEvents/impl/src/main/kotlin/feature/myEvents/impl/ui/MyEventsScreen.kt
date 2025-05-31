package feature.myEvents.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import java.util.UUID
import kotlin.collections.isNotEmpty
import data.models.EventState
import uikit.TypographyKit
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
            if (state.upComingEvents.isNotEmpty()) {
                item {
                    Text(
                        text = stringResource(UiKitR.string.upcoming_events),
                        style = TypographyKit.Heading.large,
                    )
                }
                items(state.upComingEvents) { event ->
                    UpComingEventCard(
                        event = event,
                        onClick = actions::navigateToEvent
                    )
                }
            }




            if (state.finishedEvents.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(UiKitR.string.finished_events),
                        style = TypographyKit.Heading.large
                    )
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
                            state = EventState.FINISHED,
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
