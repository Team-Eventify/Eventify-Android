package com.example.eventify.presentation.ui.events.eventdetail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.eventify.domain.models.Organization
import java.util.UUID

@Composable
fun OrganizationInfoPanel(
    organization: Organization,
    onClick: () -> Unit,
    imageLoader: ImageLoader,
) {
    val isLoading = remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model  = ImageRequest.Builder(context = LocalContext.current)
                .data(organization.photoUrl)
                .crossfade(true)
                .build(),
            imageLoader = imageLoader,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(40.dp)
                .clip(RoundedCornerShape(10.dp)),
            onLoading = {
                isLoading.value = true
            },
            onSuccess = {
                isLoading.value = false
            },
            onError = {
                isLoading.value = false
            }
        )
        Text(text = organization.title, color = MaterialTheme.colorScheme.onSecondary, fontSize = 20.sp)
    }
}

@Preview(name = "OrganizationInfoPanel")
@Composable
private fun PreviewOrganizationInfoPanel() {
    OrganizationInfoPanel(
        organization = Organization(
            id = UUID.randomUUID().toString(),
            title = LoremIpsum(2).values.joinToString(),
            description = LoremIpsum(5).values.joinToString(),
            photoUrl = UUID.randomUUID().toString(),
        ),
        onClick = {},
        imageLoader = ImageLoader(LocalContext.current)
    )
}