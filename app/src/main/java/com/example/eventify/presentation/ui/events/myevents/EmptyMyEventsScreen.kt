package com.example.eventify.presentation.ui.events.myevents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R
import com.example.eventify.presentation.ui.common.AnnotationText
import com.example.eventify.presentation.ui.common.BorderedImage
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun EmptyMyEventsScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {

        BorderedImage(painter = painterResource(id = R.drawable.round_watch_later_24))
        Text(
            text = stringResource(R.string.no_upcoming_events),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        AnnotationText(
            text = stringResource(R.string.no_upcoming_events_description),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(name = "EmptyMyEventsScreen", showSystemUi = true)
@Composable
private fun PreviewEmptyMyEventsScreen() {
    EventifyTheme(
        darkTheme = true
    ) {
        Surface {
            EmptyMyEventsScreen()
        }
    }
}