package com.example.eventify.presentation.ui.events.feedback.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.ui.theme.EventifyTheme


@Composable
fun MarkRadioButton(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onSelect,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        modifier = Modifier
            .height(50.dp)
            .aspectRatio(1f)
            .composed { modifier }
    ) {
        Text(
            text = text,
            color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(name = "MarkRadioButton")
@Composable
private fun PreviewMarkRadioButton() {
    EventifyTheme(darkTheme = true) {
        MarkRadioButton(
            text = "Text",
            selected = false,
            onSelect = {}
        )
    }
}