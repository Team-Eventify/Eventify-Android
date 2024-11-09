package com.example.eventify.presentation.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R

@Composable
fun TextInput(
    text: String,
    placeholder: String? = null,
    label: String? = null,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
) {

    OutlinedTextField(
        value = text,
        isError = isError,
        label = label?.let { { Text(it) } },
        onValueChange = onValueChange,
        placeholder = {Text(placeholder ?: "", fontSize = 17.sp, fontWeight = FontWeight.Normal)},
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = keyboardOptions,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun PasswordInput(
    text: String,
    label: String? = null,
    placeholder: String? = null,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.eye_opened)
    else
        painterResource(id = R.drawable.eye_closed)

    OutlinedTextField(
        value = text,
        isError = isError,
        label = label?.let { { Text(it) } },
        onValueChange = onValueChange,
        placeholder = {Text(placeholder ?: "", fontSize = 17.sp, fontWeight = FontWeight.Normal)},
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(
                    painter = icon,
                    contentDescription = "Visibility Icon"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = modifier
            .fillMaxWidth()
    )
}