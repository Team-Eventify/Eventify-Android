package com.example.eventify.presentation.ui.account.profileedit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.ui.account.profile.components.DeleteAccountDialog
import com.example.eventify.presentation.ui.account.profile.components.ImportantActionSettingsItem
import com.example.eventify.presentation.ui.account.profile.components.SettingsBlock
import com.example.eventify.presentation.ui.account.profileedit.components.ProfileEditInput
import com.example.eventify.presentation.ui.account.profileedit.state.ProfileEditListener
import com.example.eventify.presentation.ui.account.profileedit.state.UiState
import com.example.eventify.presentation.ui.common.AnnotationText
import com.example.eventify.presentation.ui.common.CategorySelector
import com.example.eventify.presentation.ui.common.buttons.PrimaryButton
import com.example.eventify.presentation.ui.common.PrimaryButtonText
import com.example.eventify.presentation.ui.common.SubHeadingText
import com.example.eventify.presentation.ui.common.shimmer
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.LocalDimentions
import kotlin.random.Random

@Composable
fun ProfileEditScreen(
    state: UiState.ShowProfileEdit,
    actions: ProfileEditListener,
) {
    val scrollState = rememberScrollState()
    val openDeleteAccountDialog = remember { mutableStateOf(false) }
    val dimmentions = LocalDimentions.current

    if (openDeleteAccountDialog.value) {
        DeleteAccountDialog(
            onDismissRequest = {
                openDeleteAccountDialog.value = false
            },
            onConfirmation = {
                openDeleteAccountDialog.value = false
                actions.onDeleteAccount()
            }
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(dimmentions.windowPaddings)
            .verticalScroll(scrollState)
    ) {
        SubHeadingText(stringResource(R.string.first_name))
        ProfileEditInput(
            text = state.firstName,
            onChange = actions::onChangeFirstName,
            modifier = Modifier
//                .shimmer(showShimmer = state.isLoading)
        )

        SubHeadingText(stringResource(R.string.last_name))
        ProfileEditInput(
            text = state.lastName,
            onChange = actions::onChangeLastName,
            modifier = Modifier
//                .shimmer(showShimmer = state.isLoading)
        )

        SubHeadingText(stringResource(R.string.email))
        ProfileEditInput(
            text = state.email,
            onChange = actions::onChangeEmail,
            isError = state.emailError != null || state.hasEmailError,
            supportingText = state.emailError?.asString(),
            modifier = Modifier
//                .shimmer(showShimmer = state.isLoading)
        )

        SubHeadingText(stringResource(R.string.telegram))
        ProfileEditInput(
            text = state.telegramName,
            onChange = actions::onChangeTelegram,
            prefix = "@",
            isError = state.telegramNameError != null || state.hasTelegramNameError,
            supportingText = state.telegramNameError?.asString(),
            modifier = Modifier
//                .shimmer(showShimmer = state.isLoading)
        )

        SubHeadingText(stringResource(R.string.my_categories))
        AnnotationText(text = "Выбирай категории ивентов под свои интересы!")
        CategorySelector(
            categories = state.categoryItems,
            onClickCategory = actions::onChangeCategoryFilterActive,
            modifier = Modifier
//                .shimmer(showShimmer = state.isLoading)
        )

        SettingsBlock {
            ImportantActionSettingsItem(
                text = stringResource(R.string.delete_account),
                onClick = {
                    openDeleteAccountDialog.value = true
                }
            )
        }

        PrimaryButton(onClick = actions::onSubmit) {
            PrimaryButtonText(text = stringResource(R.string.save))
        }

    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ProfileEditScreenDarkPreview() {
    EventifyTheme {
        Surface {
            ProfileEditScreen(
                state = UiState.ShowProfileEdit(
                    firstName = "Ivan",
                    lastName = "Ivanov",
                    email = "ivan@mail.ru",
                    telegramName = "ivan",
                    categoryItems = List(7) {
                        CategorySelectItem(
                            id = it.toString(),
                            title = LoremIpsum(Random.nextInt(1, 3)).values.joinToString(),
                            selected = Random.nextBoolean(),
                            color = Color.Cyan
                        )
                    }
                ),
                actions = object : ProfileEditListener {
                    override fun onSubmit() = Unit
                    override fun onChangeCategoryFilterActive(categoryId: String, value: Boolean) = Unit
                    override fun onChangeEmail(email: String) = Unit
                    override fun onChangeFirstName(firstName: String) = Unit
                    override fun onChangeLastName(lastName: String) = Unit
                    override fun onChangeTelegram(telegram: String) = Unit
                    override fun onDeleteAccount() = Unit
                    override fun onBackClick() = Unit
                }
            )
        }
    }
}
