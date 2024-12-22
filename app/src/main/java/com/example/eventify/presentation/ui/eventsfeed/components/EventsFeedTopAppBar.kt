package com.example.eventify.presentation.ui.eventsfeed.components

import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsFeedTopAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors =  TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier,
        title = {
            Text("Events Feed")
        }
    )
}

@Preview(name = "EventsFeedTopAppBar")
@Composable
private fun PreviewEventsFeedTopAppBar() {
    EventsFeedTopAppBar()
}