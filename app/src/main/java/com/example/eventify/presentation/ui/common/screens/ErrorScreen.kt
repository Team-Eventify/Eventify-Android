package com.example.eventify.presentation.ui.common.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.eventify.R
import com.example.eventify.presentation.ui.common.BorderedImage
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun ErrorScreen(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
) {
    BaseInfoScreen(
        icon = {
            BorderedImage(
                painter = painterResource(R.drawable.ic_error),
            )
        },
        title = title,
        description = description,
        modifier = modifier
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewErrorScreen() {
    EventifyTheme {
        ErrorScreen(
            title = LoremIpsum(3).values.joinToString(),
            description = LoremIpsum(10).values.joinToString()
        )
    }
}