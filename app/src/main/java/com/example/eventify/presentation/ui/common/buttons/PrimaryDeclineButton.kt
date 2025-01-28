package com.example.eventify.presentation.ui.common.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.ui.common.PrimaryButtonText
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun PrimaryDeclineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable() (RowScope.() -> Unit)
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(40.dp, 12.dp),
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        ),
        enabled = enabled,
        content = content
    )
}

@Preview
@Composable
private fun PreviewPrimaryDeclineButtonDark() {
    EventifyTheme(darkTheme = true) {
        PrimaryDeclineButton(
            onClick = {}
        ){
            PrimaryButtonText(text = "Text")
        }
    }
}

@Preview
@Composable
private fun PreviewPrimaryDeclineButtonLight() {
    EventifyTheme {
        PrimaryDeclineButton(
            onClick = {}
        ){
            PrimaryButtonText(text = "Text")
        }
    }
}