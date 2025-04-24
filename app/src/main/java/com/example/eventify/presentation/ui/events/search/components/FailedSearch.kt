package com.example.eventify.presentation.ui.events.search.components

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.R
import com.example.eventify.presentation.ui.common.buttons.PrimaryActionButton
import com.example.eventify.presentation.ui.common.screens.BaseInfoScreen
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun FailedSearch(
    message: String?,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseInfoScreen(
        icon = {
            Icon(
                painter = painterResource(R.drawable.ic_error),
                contentDescription = null,
            )
        },
        button = {
            PrimaryActionButton(
                onClick = onRefresh,
                text = stringResource(R.string.update)
            )
        },
        title = stringResource(R.string.failed_to_load_data),
        description = message,
        modifier = modifier,
    )
}

@Preview(name = "FailedSearch", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewFailedSearch() {
    FailedSearch(
        message = "Some text",
        onRefresh = {},
    )
}