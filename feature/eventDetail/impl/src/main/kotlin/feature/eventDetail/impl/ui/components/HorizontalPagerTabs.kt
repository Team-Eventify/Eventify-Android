package feature.eventDetail.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun HorizontalPagerTabs(
    pagerState: PagerState,
    onItemClick: (Int) -> Unit,
) {
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { index ->
            val color =
                if (pagerState.currentPage == index) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
                    .clickable {
                        onItemClick(index)
                    }
            )
        }
    }
}

//@Preview(name = "HorizontalPagerTabs")
//@Composable
//private fun PreviewHorizontalPagerTabs() {
//    HorizontalPagerTabs()
//}