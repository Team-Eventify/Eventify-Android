package com.example.eventify.presentation.ui.myevents

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyEventsScreen(
    modifier: Modifier = Modifier
) {
    MyEventCard()
}

@Preview(name = "MyEventsScreen", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewMyEventsScreen() {
    MyEventsScreen()
}