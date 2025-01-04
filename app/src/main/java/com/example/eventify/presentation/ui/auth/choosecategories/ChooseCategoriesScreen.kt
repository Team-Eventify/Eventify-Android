package com.example.eventify.presentation.ui.auth.choosecategories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.ui.shared.AnnotationText
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.CategorySelector
import com.example.eventify.presentation.ui.shared.buttons.PrimaryButton
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.SkipTextButton
import com.example.eventify.presentation.ui.shared.TitleText
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun ChooseCategoriesScreen(
    state: ChooseCategoriesState,
    actions: ChooseCategoriesActions,
) {

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top)
        ) {
            TitleText(text = stringResource(R.string.choose_categories_that_interest_you))

            BodyText(text = stringResource(R.string.we_will_select_event_recommendations))

            CategorySelector(
                categories = state.categoryItems,
                onClickCategory = actions.onChangeCategoryFilterActive
            )
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Bottom)
        ) {
            AnnotationText(text = stringResource(R.string.you_can_always_change_your_choice))
            PrimaryButton(
                onClick = actions.onSubmit,
                enabled = state.isValidData
            ) {
                PrimaryButtonText(text = stringResource(R.string.next))
            }

            SkipTextButton(onClick = actions.onSkip)
        }
    }
}

@Composable
@Preview(name = "ChooseCategories", showBackground = true)
private fun ChooseCategoriesScreenPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            ChooseCategoriesScreen(
                state = ChooseCategoriesState(
                    categoryItems = listOf(
                        CategorySelectItem("", "Наука", false),
                        CategorySelectItem("", "Спорт", false),
                        CategorySelectItem("", "Frontend", false),
                        CategorySelectItem("", "Backend", true),
                        CategorySelectItem("", "Media", true),
                        CategorySelectItem("", "Студенеская жизнь", false),
                        CategorySelectItem("", "Mobile", true),
                    )
                ),
                actions = ChooseCategoriesActions()
            )
        }
    }
}

