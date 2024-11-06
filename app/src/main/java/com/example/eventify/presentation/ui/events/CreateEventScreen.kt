package com.example.eventify.presentation.ui.events

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.presentation.ui.shared.CategorySelector
import com.example.eventify.presentation.ui.shared.SubHeadingText

@Composable
fun CreateEventScreen(
    modifier: Modifier = Modifier
) {
    Column {
        SubHeadingText(text = "Категории")

        CategorySelector()
    }
}

@Preview(name = "CreateEventScreen", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewCreateEventScreen() {
    CreateEventScreen()
}