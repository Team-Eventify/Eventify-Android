package com.example.eventify.presentation.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun OfflineScreen(
    title: String = stringResource(R.string.default_offline_title),
    message: String = stringResource(R.string.default_offline_message),
    onRefreshState: () -> Unit
) {
    Surface(
        contentColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            BorderedImage(
                painter = painterResource(R.drawable.ic_no_internet)
            )

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            AnnotationText(
                text = message,
            )

            UpdateStatusButton(
                text = stringResource(R.string.update),
                onClick = onRefreshState
            )
        }
    }
}

@Preview(name = "OfflineComponent", showBackground = true)
@Composable
private fun PreviewOfflineComponent() {
    EventifyTheme(darkTheme = true) {
        Surface {
            OfflineScreen(
                title = "You are offline",
                message = "Offline data will be later",
                onRefreshState = {}
            )
        }
    }
}