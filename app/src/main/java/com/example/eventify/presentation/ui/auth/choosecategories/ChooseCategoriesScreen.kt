package com.example.eventify.presentation.ui.auth.choosecategories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.ui.auth.choosecategories.state.SetUpListener
import com.example.eventify.presentation.ui.auth.choosecategories.state.SetUpState
import com.example.eventify.presentation.ui.common.AnnotationText
import com.example.eventify.presentation.ui.common.BodyText
import com.example.eventify.presentation.ui.common.CategorySelector
import com.example.eventify.presentation.ui.common.buttons.PrimaryButton
import com.example.eventify.presentation.ui.common.PrimaryButtonText
import com.example.eventify.presentation.ui.common.SkipTextButton
import com.example.eventify.presentation.ui.common.TitleText
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.LocalDimentions
import com.example.eventify.presentation.ui.theme.space20
import java.util.UUID

@Composable
fun ChooseCategoriesScreen(
    state: SetUpState,
    actions: SetUpListener,
) {
    val dimmentions = LocalDimentions.current

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimmentions.windowPaddings)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top)
        ) {
            TitleText(text = stringResource(R.string.choose_categories_that_interest_you))

            BodyText(text = stringResource(R.string.we_will_select_event_recommendations))

            CategorySelector(
                categories = state.categoryItems,
                onClickCategory = actions::selectCategory
            )
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space20, Alignment.Bottom)
        ) {
            AnnotationText(text = stringResource(R.string.you_can_always_change_your_choice))
            PrimaryButton(
                onClick = actions::nextStep,
                enabled = state.isValidData
            ) {
                PrimaryButtonText(text = stringResource(R.string.next))
            }

            SkipTextButton(onClick = actions::skip)
        }
    }
}

@Composable
@Preview(name = "ChooseCategories", showBackground = true)
private fun ChooseCategoriesScreenPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            ChooseCategoriesScreen(
                state = SetUpState(
                    categoryItems = List(7) {
                        CategorySelectItem(id = UUID.randomUUID().toString(), title = LoremIpsum().values.joinToString(), selected = false, color = Color.Cyan)
                    }
                ),
                actions = object : SetUpListener {
                    override fun selectCategory(categoryId: String, selected: Boolean) = Unit
                    override fun skip() = Unit
                    override fun nextStep() = Unit
                }
            )
        }
    }
}

