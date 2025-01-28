package com.example.eventify.presentation.ui.common.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.R
import com.example.eventify.presentation.ui.common.BorderedImage
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun NoInternetConnectionScreen(
    modifier: Modifier = Modifier,
) {
    BaseInfoScreen(
        title = stringResource(R.string.default_offline_title),
        description = stringResource(R.string.default_offline_message),
        icon = {
            BorderedImage(painter = painterResource(R.drawable.ic_no_internet))
        },
        button = {

        },
        modifier = modifier
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NoInternetConnectionScreenPreview() {
    EventifyTheme {
        NoInternetConnectionScreen()
    }
}