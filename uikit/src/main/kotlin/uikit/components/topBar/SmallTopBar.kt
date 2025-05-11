package uikit.components.topBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopBar(
    state: TopBarState.Base,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = state.title)
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
