package uikit.components.topBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import uikit.EventifyTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopBar(
    state: TopBarState.Base,
    modifier: Modifier = Modifier,
) {
    MediumTopAppBar(
        title = {
            state.title?.let {
                Text(
                    text = it,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

        },
        navigationIcon = {
            state.leftAction?.let { action ->
                IconButton(
                    onClick = action.onClick
                ) {
                    Icon(
                        painter = painterResource(action.iconRes),
                        contentDescription = ""
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = state.containerColor ?: Color.Transparent,
        ),
        modifier = Modifier
            .then(modifier)

    )
}

@Preview
@Composable
private fun PreviewMediumTopBar() {
    EventifyTheme(darkTheme = true) {
        MediumTopBar(
            state = TopBarState.Base(
                title = "afsdfasdfdsafsadfasdfasdfasdfasdf",
                size = TopBarSize.MEDIUM
            )
        )
    }
}