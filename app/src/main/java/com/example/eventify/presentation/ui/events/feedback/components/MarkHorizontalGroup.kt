package com.example.eventify.presentation.ui.events.feedback.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun MarkHorizontalGroup(
    options: List<Pair<Int, String>>,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement. Horizontal = Arrangement.Start,
    verticalAlignment: Alignment. Vertical = Alignment.Top,
) {
    var selectedItem by remember { mutableIntStateOf(-1) }

    Row(
        modifier = Modifier
            .composed { modifier },
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        options.forEach { (key, value) ->
            MarkRadioButton(
                onSelect = {
                    selectedItem = key
                },
                selected = key <= selectedItem,
                text = value
            )
        }
    }
}

@Preview(name = "MarkHorizontalGroup")
@Composable
private fun PreviewMarkHorizontalGroup() {
    EventifyTheme(darkTheme = true) {
        MarkHorizontalGroup(
            options = List(5){ it to it.toString() }
        )
    }
}