package feature.setup.impl.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import domain.models.CategorySelectItem
import feature.setup.impl.state.MainSetupUiState
import feature.setup.impl.state.SetUpListener
import uikit.EventifyTheme
import uikit.LocalDimentions
import uikit.TypographyKit
import uikit.components.CategorySelectChip
import uikit.components.TitleText
import uikit.components.buttons.PrimaryButtonWithLoader
import uikit.space20
import com.example.eventify.uikit.R as UiKitR


@Composable
fun ChooseCategories(
    state: MainSetupUiState,
    actions: SetUpListener,
) {
    val dimmentions = LocalDimentions.current
    val isEnabledFlowNext = remember(state) {
        state.categoriesState.categories.isNotEmpty()
    }

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(dimmentions.windowPaddings)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top)
        ) {
            TitleText(text = stringResource(UiKitR.string.choose_categories_that_interest_you))

            Text(
                text = stringResource(UiKitR.string.we_will_select_event_recommendations),
                style = TypographyKit.bodyRegular,
            )

            CategorySelectorFlowRow(
                categories = state.categoriesState.categories,
                onCategoryClick = actions::selectCategory
            )
            state.categoriesState.error?.let {
                Text(
                    text = it,
                    style = TypographyKit.bodyRegular,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space20, Alignment.Bottom)
        ) {
            Text(
                text = stringResource(UiKitR.string.you_can_always_change_your_choice),
                style = TypographyKit.bodyRegular,
            )
            PrimaryButtonWithLoader(
                text = stringResource(UiKitR.string.next),
                onClick = actions::flowNext,
                isEnabled = isEnabledFlowNext
            )
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CategorySelectorFlowRow(
    categories: List<CategorySelectItem>,
    onCategoryClick: (String, Boolean) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .defaultMinSize(minHeight = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
    ){
        categories.forEach {
            CategorySelectChip(
                title = it.title,
                isSelected = it.selected,
                color = it.color,
                onSelect = {
                    onCategoryClick(it.id, !it.selected)
                },
            )
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ChooseCategoriesPreview() {
    EventifyTheme {
        ChooseCategories(
            state = MainSetupUiState(),
            actions = object : SetUpListener {
                override fun selectCategory(categoryId: String, selected: Boolean) = Unit
                override fun onChangeFirstName(value: String) = Unit
                override fun onChangeLastName(value: String) = Unit
                override fun flowNext() = Unit
                override fun flowBack() = Unit
            }
        )
    }
}