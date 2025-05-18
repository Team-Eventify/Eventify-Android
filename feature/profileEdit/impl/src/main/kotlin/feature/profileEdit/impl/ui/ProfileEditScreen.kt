package feature.profileEdit.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import domain.models.CategorySelectItem
import feature.profileEdit.api.ProfileEditListener
import feature.profileEdit.impl.components.CategorySelector
import feature.profileEdit.impl.components.DeleteAccountDialog
import feature.profileEdit.impl.components.ProfileEditInput
import feature.profileEdit.impl.state.UiState
import uikit.EventifyTheme
import uikit.LocalDimentions
import uikit.TypographyKit
import uikit.components.ImportantActionSettingsItem
import uikit.components.SettingsBlock
import uikit.components.buttons.PrimaryButtonWithLoader
import kotlin.random.Random
import com.example.eventify.uikit.R as UiKitR
import com.example.eventify.feature.profileEdit.impl.R as EditR


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
            .background(MaterialTheme.colorScheme.background)
            .padding(dimmentions.windowPaddings)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = stringResource(UiKitR.string.first_name),
            style = TypographyKit.bodyMedium
        )
        ProfileEditInput(
            text = state.firstName,
            onChange = actions::onChangeFirstName,
            modifier = Modifier
        )

        Text(
            text = stringResource(UiKitR.string.last_name),
            style = TypographyKit.bodyMedium,
        )
        ProfileEditInput(
            text = state.lastName,
            onChange = actions::onChangeLastName,
            modifier = Modifier
        )

        Text(
            text = stringResource(UiKitR.string.email),
            style = TypographyKit.bodyMedium,
        )
        ProfileEditInput(
            text = state.email,
            onChange = actions::onChangeEmail,
            isError = state.emailError != null || state.hasEmailError,
            supportingText = state.emailError,
            modifier = Modifier
        )

        Text(
            text = stringResource(UiKitR.string.telegram),
            style = TypographyKit.bodyMedium,
        )
        ProfileEditInput(
            text = state.telegramName,
            onChange = actions::onChangeTelegram,
            prefix = "@",
            isError = state.telegramNameError != null || state.hasTelegramNameError,
            supportingText = state.telegramNameError,
            modifier = Modifier
        )

        Text(
            text = stringResource(UiKitR.string.my_categories),
            style = TypographyKit.bodyMedium,
        )
        Text(
            text = stringResource(EditR.string.choose_categorie_actions),
            style = TypographyKit.bodyRegular,
        )
        CategorySelector(
            categories = state.categoryItems,
            onClickCategory = actions::onChangeCategoryFilterActive,
            modifier = Modifier
        )
        state.categoriesError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = TypographyKit.bodyRegular,
            )
        }


        SettingsBlock {
            ImportantActionSettingsItem(
                text = stringResource(UiKitR.string.delete_account),
                onClick = {
                    openDeleteAccountDialog.value = true
                }
            )
        }

        PrimaryButtonWithLoader(
            onClick = actions::onSubmit,
            text = stringResource(UiKitR.string.save)
        )
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
