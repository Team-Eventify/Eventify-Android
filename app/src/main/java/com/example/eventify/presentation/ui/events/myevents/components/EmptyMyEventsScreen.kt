package com.example.eventify.presentation.ui.events.myevents.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.R
import com.example.eventify.presentation.ui.common.BorderedImage
import com.example.eventify.presentation.ui.common.buttons.PrimaryActionButton
import com.example.eventify.presentation.ui.common.screens.BaseInfoScreen

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