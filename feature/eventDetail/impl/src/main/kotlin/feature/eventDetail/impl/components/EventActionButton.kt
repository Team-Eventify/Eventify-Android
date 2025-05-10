package feature.eventDetail.impl.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.R
import com.example.eventify.domain.models.EventState
import com.example.eventify.domain.models.isSubscribeEnabled
import com.example.eventify.presentation.ui.common.PrimaryButtonText
import com.example.eventify.presentation.ui.common.buttons.PrimaryButton
import com.example.eventify.presentation.ui.common.buttons.PrimaryDeclineButton
import com.example.eventify.presentation.ui.theme.EventifyTheme


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
            PrimaryButtonText(text = stringResource(R.string.unsubscribe_for_event_action))
        }
    } else {
        PrimaryButton(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier,
        ) {
            PrimaryButtonText(text = stringResource(R.string.subscribe_for_event_action))
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

