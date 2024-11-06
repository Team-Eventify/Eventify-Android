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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.ui.shared.AnnotationText
import com.example.eventify.presentation.ui.shared.CategorySelector
import com.example.eventify.presentation.ui.shared.PrimaryButton
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.SubHeadingText
import com.example.eventify.presentation.ui.shared.TextInput

@Composable
fun ProfileEditScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(10.dp)
    ) {
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var middleName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var telegram by remember { mutableStateOf("") }


        SubHeadingText(text = "Имя")
        TextInput(
            text = firstName,
            placeholder = "Иван",
            onValueChange = {
                firstName = it
            }
        )

        SubHeadingText(text = "Фамилия")
        TextInput(
            text = lastName,
            placeholder = "Иванов",
            onValueChange = {
                lastName = it
            }
        )

        SubHeadingText(text = "Отчество")
        TextInput(
            text = middleName,
            placeholder = "Иванович",
            onValueChange = {
                middleName = it
            }
        )

        SubHeadingText(text = "Электронная почта")
        TextInput(
            text = email,
            placeholder = "ivanov@gmail.com",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            onValueChange = {
                email = it
            }
        )

        SubHeadingText(text = "Telegram")
        TextInput(
            text = telegram,
            placeholder = "@ivanov",
            onValueChange = {
                telegram = it
            }
        )

        Spacer(Modifier.height(10.dp))
        Divider()

        SubHeadingText(text = "Мои категории")
        AnnotationText(text = "Выбирай категории ивентов под свои интересы!")

        CategorySelector()

        PrimaryButton(onClick = { /*TODO*/ }) {
            PrimaryButtonText(text = "Сохранить изменения")

        }

    }
}

@Preview(name = "ProfileEditScreen", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewProfileEditScreen() {
    ProfileEditScreen()
}