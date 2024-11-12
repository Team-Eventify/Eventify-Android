package com.example.eventify.presentation.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R

@Composable
fun OfflineComponent(
    title: String? = stringResource(R.string.default_offline_title),
    message: String? = stringResource(R.string.default_offline_message),
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(.5f),
                painter = painterResource(id = R.drawable.outline_cloud_off_24),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )

            title?.let {
                Text(
                    text = it,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            message?.let {
                Text(
                    text = it,
                    fontSize = 20.sp
                )
            }
        }
    }

}

@Preview(name = "OfflineComponent", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewOfflineComponent() {
    OfflineComponent(
        title = "You are offline",
        message = "Offline data will be later"
    )
}