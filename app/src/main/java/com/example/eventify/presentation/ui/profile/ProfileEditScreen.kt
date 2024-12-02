package com.example.eventify.presentation.ui.profile

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.eventify.data.models.UserInfo
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.models.UserResult
import com.example.eventify.presentation.models.UserUiState
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import com.example.eventify.presentation.ui.shared.AnnotationText
import com.example.eventify.presentation.ui.shared.CategorySelector
import com.example.eventify.presentation.ui.shared.PrimaryButton
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.SubHeadingText
import com.example.eventify.presentation.ui.shared.TextInput
import com.example.eventify.presentation.viewmodels.UserViewModel

@Composable
fun ProfileEditScreen(
    navController: NavHostController,
    viewModel: UserViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    ProfileEditScreenComponent(
        uiState = viewModel.uiState,
        currentUser = viewModel.user,
        changeUserResult = viewModel.changeUserResult,
        loadUserResult = viewModel.loadUserResult,
        onChangeUser = viewModel::changeUser,
        onChangeFirstName = viewModel::changeFirstName,
        onChangeLastName = viewModel::changeLastName,
        onChangeMiddleName = viewModel::changeMiddleName,
        onChangeEmail = viewModel::changeEmail,
        onChangeTelegram = viewModel::changeTelegram,
        onLoadCurrentUser = viewModel::loadUserInfo,
        categories = viewModel.userCategories,
        onCategoryClick = viewModel::toggleCategorySelection
    )
}


@Composable
fun ProfileEditScreenComponent(
    uiState: UserUiState,
    currentUser: UserInfo?,
    loadUserResult: UserResult,
    changeUserResult: UserResult,
    onChangeUser: () -> Unit,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeMiddleName: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangeTelegram: (String) -> Unit,
    onLoadCurrentUser: () -> Unit,
    categories: List<CategorySelectItem>,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(true) {
        onLoadCurrentUser()
    }

    LaunchedEffect(loadUserResult) {
        if (loadUserResult is UserResult.Error){
            SnackbarController.sendEvent(
                event = SnackbarEvent(
                    message = loadUserResult.message
                )
            )
        }
    }

    LaunchedEffect(changeUserResult) {
        when (changeUserResult){
            is UserResult.Success -> {
                onLoadCurrentUser()
                SnackbarController.sendEvent(
                    event = SnackbarEvent(
                        message = "Профиль обновлен."
                    )
                )
            }
            is UserResult.Error -> {
                SnackbarController.sendEvent(
                    event = SnackbarEvent(
                        message = changeUserResult.message
                    )
                )
            }
            else -> null
        }
    }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(10.dp)
    ) {
        SubHeadingText(text = "Имя")
        TextInput(
            text = uiState.firstName,
            placeholder = "Иван",
            onValueChange = onChangeFirstName
        )

        SubHeadingText(text = "Фамилия")
        TextInput(
            text = uiState.lastName,
            placeholder = "Иванов",
            onValueChange = onChangeLastName
        )

        SubHeadingText(text = "Отчество")
        TextInput(
            text = uiState.middleName,
            placeholder = "Иванович",
            onValueChange = onChangeMiddleName
        )

        SubHeadingText(text = "Электронная почта")
        TextInput(
            text = uiState.email,
            placeholder = "ivanov@gmail.com",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            onValueChange = onChangeEmail
        )

        SubHeadingText(text = "Telegram")
        TextInput(
            text = uiState.telegramName,
            placeholder = "@ivanov",
            onValueChange = onChangeTelegram
        )

        Spacer(Modifier.height(10.dp))
        HorizontalDivider()

        SubHeadingText(text = "Мои категории")
        AnnotationText(text = "Выбирай категории ивентов под свои интересы!")

        CategorySelector(
            categories = categories,
            onClickCategory = onCategoryClick
        )

        PrimaryButton(onClick = onChangeUser ) {
            PrimaryButtonText(text = "Сохранить изменения")

        }

    }
}

@Preview(name = "ProfileEditScreen", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewProfileEditScreen() {
    ProfileEditScreenComponent(
        uiState = UserUiState(
            email = "werwer",
            firstName = "xxcv",
            lastName = "vfvf",
            middleName = "asda",
            telegramName = "vsdvds",
        ),
        currentUser = UserInfo(
            email = "werwer",
            firstName = "xxcv",
            id = "qweqwe",
            lastName = "vfvf",
            middleName = "asda",
            telegramName = "vsdvds",
        ),
        changeUserResult = UserResult.Idle,
        loadUserResult = UserResult.Idle,
        onChangeFirstName = {},
        onChangeLastName = {},
        onChangeMiddleName = {},
        onChangeEmail = {},
        onChangeTelegram = {},
        onChangeUser = {},
        onLoadCurrentUser = {},
        categories = listOf(
            CategorySelectItem(
                id = "",
                title = "Backend",
                selected = true
            ),
            CategorySelectItem(
                id = "",
                title = "Frontend",
                selected = false
            ),
            CategorySelectItem(
                id = "",
                title = "Сопрт",
                selected = false
            ),
            CategorySelectItem(
                id = "",
                title = "Наука",
                selected = false
            ),
            CategorySelectItem(
                id = "",
                title = "Game Dev",
                selected = true
            ),

            ),
        onCategoryClick = {}
    )
}