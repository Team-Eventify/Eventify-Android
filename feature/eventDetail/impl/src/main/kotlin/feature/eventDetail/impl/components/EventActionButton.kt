package feature.eventDetail.impl.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import data.models.EventState
import domain.extentions.isSubscribeEnabled
import uikit.EventifyTheme
import uikit.components.PrimaryButtonText
import uikit.components.buttons.PrimaryButton
import uikit.components.buttons.PrimaryDeclineButton
import com.example.eventify.uikit.R as UiKitR



@Composable
fun EventActionButton(
    eventState: EventState,
    isSubscribed: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val enabled = remember {
        eventState.isSubscribeEnabled()
    }

    if (isSubscribed) {
        PrimaryDeclineButton(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier,
        ) {
            PrimaryButtonText(text = stringResource(UiKitR.string.unsubscribe_for_event_action))
        }
    } else {
        PrimaryButton(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier,
        ) {
            PrimaryButtonText(text = stringResource(UiKitR.string.subscribe_for_event_action))
        }
    }




}

@Preview(name = "EventActionButton")
@Composable
private fun PreviewEventActionButton() {
    EventifyTheme {
        EventActionButton(
            eventState = EventState.PUBLISHED,
            isSubscribed = false,
            onClick = {}
        )
    }
}

