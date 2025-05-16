package feature.eventDetail.impl.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.feature.eventDetail.impl.R
import data.models.EventState
import domain.extentions.isSubscribeEnabled
import uikit.EventifyTheme
import uikit.components.buttons.PrimaryButtonWithLoader
import com.example.eventify.uikit.R as UiKitR



@Composable
internal fun EventActionButtonContainer(
    eventState: EventState,
    isSubscribed: Boolean,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    val buttonText = remember {
        when {
            !eventState.isSubscribeEnabled() -> context.getString(R.string.event_finished)
            isSubscribed -> context.getString(UiKitR.string.unsubscribe_for_event_action)
            !isSubscribed -> context.getString(UiKitR.string.subscribe_for_event_action)
            else -> context.getString(R.string.event_unavailable)
        }
    }
    val buttonColor = MaterialTheme.colorScheme.errorContainer.takeIf { isSubscribed } ?: MaterialTheme.colorScheme.primary

    PrimaryButtonWithLoader(
        text = buttonText,
        onClick = onClick,
        color = buttonColor,
        isLoading = isLoading,
        isEnabled = eventState.isSubscribeEnabled(),
        modifier = modifier,
    )
}

@Preview(name = "EventActionButton")
@Composable
private fun PreviewEventActionButton() {
    EventifyTheme {
        EventActionButtonContainer(
            eventState = EventState.PUBLISHED,
            isSubscribed = false,
            onClick = {},
        )
    }
}

