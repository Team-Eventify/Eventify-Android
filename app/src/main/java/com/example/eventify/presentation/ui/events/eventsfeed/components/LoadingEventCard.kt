package com.example.eventify.presentation.ui.events.eventsfeed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.shared.shimmer
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun LoadingEventCard(
    blendMode: BlendMode = BlendMode.SrcAtop
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .shimmer(
                    showShimmer = true,
                    blendMode = blendMode
                )
        ) {
            Image(
                painter = painterResource(R.drawable.underfind_event_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))

            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .height(30.dp)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(60.dp))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(60.dp))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(60.dp))
            )
        }
    }

}

@Preview(name = "LoadingEventCard")
@Composable
private fun PreviewLoadingEventCard() {
    EventifyTheme(darkTheme = true) {
        LoadingEventCard()
    }
}