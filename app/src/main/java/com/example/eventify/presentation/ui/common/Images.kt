package com.example.eventify.presentation.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.eventify.R
import com.example.eventify.presentation.LocaleImageLoader
import com.example.eventify.presentation.utils.conditional

@Composable
fun EventImage(
    uri: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillWidth,
    shimmerMode: BlendMode = BlendMode.SrcAtop,
    alignment: Alignment = Alignment.Center,
) {
    val localeImageLoader = LocaleImageLoader.current
    var showShimmer by remember { mutableStateOf(false) }

    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(uri)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.underfind_event_image),
        placeholder = painterResource(R.drawable.underfind_event_image),
        contentDescription = null,
        contentScale = contentScale,
        alignment = alignment,
        imageLoader = localeImageLoader,
        onLoading = {
            showShimmer = true
        },
        onError = {
            showShimmer = false
        },
        onSuccess = {
            showShimmer = false
        },
        modifier = Modifier
            .animateContentSize()
            .shimmer(
                showShimmer = showShimmer,
                blendMode = shimmerMode,
            )
            .then(modifier)
    )
}