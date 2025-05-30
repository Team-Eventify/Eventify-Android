package feature.myEvents.impl.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uikit.components.BorderedImage
import uikit.components.buttons.PrimaryActionButton
import uikit.components.screens.BaseInfoScreen
import com.example.eventify.uikit.R as UiKitR


@Composable
fun EmptyMyEventsScreen(
    onActionClick: () -> Unit,
) {
    BaseInfoScreen(
        title = stringResource(UiKitR.string.no_upcoming_events),
        description = stringResource(UiKitR.string.no_upcoming_events_description),
        icon = {
            BorderedImage(painter = painterResource(id = UiKitR.drawable.calendar))
        },
        button = {
            PrimaryActionButton(
                onClick = onActionClick,
                text = stringResource(UiKitR.string.go_to_events)
            )
        }
    )
}

@Preview(name = "NoMyEvents")
@Composable
private fun PreviewNoMyEvents() {
    EmptyMyEventsScreen(
        onActionClick = {}
    )
}