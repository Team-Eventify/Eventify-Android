package com.example.eventify.presentation.ui.account.profileedit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.ui.account.profileedit.components.ProfileEditInput
import com.example.eventify.presentation.ui.shared.AnnotationText
import com.example.eventify.presentation.ui.shared.CategorySelector
import com.example.eventify.presentation.ui.shared.buttons.PrimaryButton
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.SubHeadingText

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
            onChange = actions.onChangeFirstName
        )

        SubHeadingText(text = "Фамилия")
        ProfileEditInput(
            text = state.lastName,
            onChange = actions.onChangeLastName
        )

        SubHeadingText(text = "Отчество")
        ProfileEditInput(
            text = state.middleName,
            onChange = actions.onChangeMiddleName
        )

        SubHeadingText(text = "Электронная поста")
        ProfileEditInput(
            text = state.email,
            onChange = actions.onChangeEmail,
            isError = state.emailError != null || state.hasEmailError,
            supportingText = state.emailError
        )

        SubHeadingText(text = "Telegram")
        ProfileEditInput(
            text = state.telegramName,
            onChange = actions.onChangeTelegram,
            prefix = "@",
            isError = state.telegramNameError != null || state.hasTelegramNameError,
            supportingText = state.telegramNameError
        )

        SubHeadingText(text = "Мои категории")
        AnnotationText(text = "Выбирай категории ивентов под свои интересы!")
        CategorySelector(
            categories = state.categoryItems,
            onClickCategory = actions.onToggleCategoryItem
        )

        PrimaryButton(onClick = actions.onSubmit) {
            PrimaryButtonText(text = "Сохранить")
        }

    }
}

@Composable
@Preview(name = "ProfileEdit", showBackground = true, showSystemUi = true)
private fun ProfileEditScreenPreview() {
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
                    selected = false
                ),
                CategorySelectItem(
                    id = "",
                    title = "Game Dev",
                    selected = true
                ),
                CategorySelectItem(
                    id = "",
                    title = "Communication",
                    selected = false
                ),
                CategorySelectItem(
                    id = "",
                    title = "Backend",
                    selected = true
                ),
                CategorySelectItem(
                    id = "",
                    title = "Mobile",
                    selected = true
                ),
                CategorySelectItem(
                    id = "",
                    title = "Frontend",
                    selected = false
                ),

            )
        ),
        actions = ProfileEditActions.default()
    )
}

