package com.example.eventify.presentation.ui.events.eventsfeed.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun LoadingEventsFeedView(
    modifier: Modifier = Modifier,
    count: Int = 6,
    cardBlendMode: BlendMode = BlendMode.SrcAtop
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 15.dp),
        modifier = Modifier
            .fillMaxSize()
            .composed { modifier }
    ){
        items(count){
            LoadingEventCard(
                blendMode = cardBlendMode
            )
            Spacer(Modifier.height(10.dp))
            HorizontalDivider()
        }
    }
}

@Preview(name = "LoadingEventsFeedView")
@Composable
private fun PreviewLoadingEventsFeedViewDark() {
    EventifyTheme(darkTheme = true) {
        Surface {
            LoadingEventsFeedView()

        }
    }
}