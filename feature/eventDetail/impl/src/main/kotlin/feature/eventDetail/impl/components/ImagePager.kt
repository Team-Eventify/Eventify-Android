package feature.eventDetail.impl.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ImagePager(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    key: ((Int) -> Any)? = null,
    pageContent: @Composable() (PagerScope.(page: Int) -> Unit),
) {
    val coroutineScope = rememberCoroutineScope()
    val isSingleImage = remember(pagerState) { pagerState.pageCount <= 1 }

    Column(
        modifier.fillMaxWidth()
    ) {

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = !isSingleImage,
            pageSpacing = 15.dp,
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            pageContent(page)
        }

        if (!isSingleImage){
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
}