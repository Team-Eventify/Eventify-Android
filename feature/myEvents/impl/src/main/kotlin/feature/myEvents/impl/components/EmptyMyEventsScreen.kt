package feature.myEvents.impl.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.R
import uikit.components.BorderedImage
import uikit.components.buttons.PrimaryActionButton
import uikit.components.screens.BaseInfoScreen

@Composable
fun EmptyMyEventsScreen(
    onActionClick: () -> Unit,
) {
    BaseInfoScreen(
        title = stringResource(R.string.no_upcoming_events),
        description = stringResource(R.string.no_upcoming_events_description),
        icon = {
            BorderedImage(painter = painterResource(id = R.drawable.calendar))
        },
        button = {
            PrimaryActionButton(
                onClick = onActionClick,
                text = stringResource(R.string.go_to_events)
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