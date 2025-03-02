package com.example.eventify.presentation.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.eventify.R
import com.example.eventify.presentation.LocaleImageLoader

@Composable
fun EventImage(
    uri: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    alignment: Alignment = Alignment.Center
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
            .then(modifier)
//            .fillMaxWidth()
//            .height(200.dp)
//            .clip(RoundedCornerShape(10.dp))
            .shimmer(
                showShimmer = showShimmer,
                blendMode = BlendMode.SrcAtop
            )
    )
}