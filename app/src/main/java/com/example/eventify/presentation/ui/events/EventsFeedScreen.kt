package com.example.eventify.presentation.ui.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.presentation.ui.shared.EventCard
import com.example.eventify.presentation.ui.shared.HeadingText

@Composable
fun EventsFeedScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HeadingText("Популярные ивенты")

        (1..5).forEach{
            EventCard()
            Divider()
        }

        HeadingText("Категории на основе твоих интересов")



        HeadingText("Ивенты, которые тебе понравятся")

    }
}

@Preview(name = "EventsFeedScreen", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewEventsFeedScreen() {
    EventsFeedScreen()
}