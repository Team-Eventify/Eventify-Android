package uikit.components.topBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun EventifyTopAppBar(
    state: TopBarState,
    modifier: Modifier = Modifier
) {
    when (state) {
        TopBarState.None -> {}
        is TopBarState.Base -> {
            when (state.size) {
                TopBarSize.SMALL, TopBarSize.UNKNOWN -> {
                    SmallTopBar(state, modifier)
                }
                TopBarSize.MEDIUM -> {
                    MediumTopBar(state, modifier)
                }
                TopBarSize.LARGE -> TODO()
            }
        }
        is TopBarState.Search -> {
            SearchTopBar(
                value = state.searchText,
                onValueChanged = state.onChangeText,
                onLeadingIconClick = state.onLeadingIconClick,
                onTrailingIconClick = state.onTrailingIconClick,
                modifier = modifier
            )
        }

        is TopBarState.ExpandedSearch -> TODO()
    }

}