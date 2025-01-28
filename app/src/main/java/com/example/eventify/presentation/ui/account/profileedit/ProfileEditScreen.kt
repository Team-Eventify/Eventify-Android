package com.example.eventify.presentation.ui.account.profileedit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.ui.account.profileedit.components.ProfileEditInput
import com.example.eventify.presentation.ui.common.AnnotationText
import com.example.eventify.presentation.ui.common.CategorySelector
import com.example.eventify.presentation.ui.common.buttons.PrimaryButton
import com.example.eventify.presentation.ui.common.PrimaryButtonText
import com.example.eventify.presentation.ui.common.SubHeadingText
import com.example.eventify.presentation.ui.common.shimmer
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun ProfileEditScreen(
    state: ProfileEditState,
    actions: ProfileEditActions,
) {
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(scrollState)
    ) {
        SubHeadingText(text = "Имя")
        ProfileEditInput(
            text = state.firstName,
            onChange = actions.onChangeFirstName,
            modifier = Modifier
                .shimmer(showShimmer = state.isLoading)
        )

        SubHeadingText(text = "Фамилия")
        ProfileEditInput(
            text = state.lastName,
            onChange = actions.onChangeLastName,
            modifier = Modifier
                .shimmer(showShimmer = state.isLoading)
        )

        SubHeadingText(text = "Отчество")
        ProfileEditInput(
            text = state.middleName,
            onChange = actions.onChangeMiddleName,
            modifier = Modifier
                .shimmer(showShimmer = state.isLoading)
        )

        SubHeadingText(text = "Электронная поста")
        ProfileEditInput(
            text = state.email,
            onChange = actions.onChangeEmail,
            isError = state.emailError != null || state.hasEmailError,
            supportingText = state.emailError?.asString(),
            modifier = Modifier
                .shimmer(showShimmer = state.isLoading)
        )

        SubHeadingText(text = "Telegram")
        ProfileEditInput(
            text = state.telegramName,
            onChange = actions.onChangeTelegram,
            prefix = "@",
            isError = state.telegramNameError != null || state.hasTelegramNameError,
            supportingText = state.telegramNameError?.asString(),
            modifier = Modifier
                .shimmer(showShimmer = state.isLoading)
        )

        SubHeadingText(text = "Мои категории")
        AnnotationText(text = "Выбирай категории ивентов под свои интересы!")
        CategorySelector(
            categories = state.categoryItems,
            onClickCategory = actions.onChangeCategoryFilterActive,
            modifier = Modifier
                .shimmer(showShimmer = state.isLoading)
        )

        PrimaryButton(onClick = actions.onSubmit) {
            PrimaryButtonText(text = "Сохранить")
        }

    }
}

@Composable
@Preview(name = "ProfileEdit", showBackground = true)
private fun ProfileEditScreenDarkPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            ProfileEditScreen(
                state = ProfileEditState(
                    firstName = "Ivan",
                    lastName = "Ivanov",
                    middleName = "Ivanovich",
                    email = "ivan@mail.ru",
                    telegramName = "ivan",
                    categoryItems = listOf(
                        CategorySelectItem(
                            id = "",
                            title = "Sport",
                            selected = false,
                            color = Color.Cyan
                        ),
                        CategorySelectItem(
                            id = "",
                            title = "Game Dev",
                            selected = true,
                            color = Color.Red
                        ),
                        CategorySelectItem(
                            id = "",
                            title = "Communication",
                            selected = false,
                            color = Color.Cyan
                        ),
                        CategorySelectItem(
                            id = "",
                            title = "Backend",
                            selected = true,
                            color = Color.Green
                        ),
                        CategorySelectItem(
                            id = "",
                            title = "Mobile",
                            selected = true,
                            color = Color.Blue
                        ),
                        CategorySelectItem(
                            id = "",
                            title = "Frontend",
                            selected = false,
                            color = Color.Magenta
                        ),

                        )
                ),
                actions = ProfileEditActions()
            )
        }
    }
}

@Composable
@Preview(name = "ProfileEdit", showBackground = true, showSystemUi = true)
private fun ProfileEditScreenLightPreview() {
    EventifyTheme {
        Surface {
            ProfileEditScreen(
                state = ProfileEditState(
                    firstName = "Ivan",
                    lastName = "Ivanov",
                    middleName = "Ivanovich",
                    email = "ivan@mail.ru",
                    telegramName = "ivan",
                    categoryItems = listOf(
                        CategorySelectItem(
                            id = "",
                            title = "Sport",
                            selected = false,
                            color = Color.Cyan
                        ),
                        CategorySelectItem(
                            id = "",
                            title = "Game Dev",
                            selected = true,
                            color = Color.Red
                        ),
                        CategorySelectItem(
                            id = "",
                            title = "Communication",
                            selected = false,
                            color = Color.Cyan
                        ),
                        CategorySelectItem(
                            id = "",
                            title = "Backend",
                            selected = true,
                            color = Color.Green
                        ),
                        CategorySelectItem(
                            id = "",
                            title = "Mobile",
                            selected = true,
                            color = Color.Blue
                        ),
                        CategorySelectItem(
                            id = "",
                            title = "Frontend",
                            selected = false,
                            color = Color.Magenta
                        ),

                        )
                ),
                actions = ProfileEditActions()
            )
        }
    }
}

