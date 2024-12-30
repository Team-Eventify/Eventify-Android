package com.example.eventify.presentation.ui.events.eventdetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.theme.EventifyTheme
import kotlinx.coroutines.launch

@Composable
fun ImagePager(
    images: List<Painter>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { images.count() })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier.fillMaxWidth()
    ) {

        HorizontalPager(
            state = pagerState,
            pageSpacing = 15.dp,
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            Image(
                painter = images[page],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .height(250.dp)
            )
        }

        HorizontalPagerTabs(
            pagerState = pagerState,
            onItemClick = { index ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }
        )


    }
}

@Preview(showBackground = true)
@Composable
private fun ImagePagerPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            ImagePager(
                images = listOf(
                    painterResource(R.drawable.default_event_image),
                    painterResource(R.drawable.misis_cj_image),
                    painterResource(R.drawable.default_event_image),
                )
            )
        }
    }
}