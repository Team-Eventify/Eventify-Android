package com.example.eventify.presentation.ui.common.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.suspendCancellableCoroutine

@Composable
fun OtpTextField(
    text: String,
    modifier: Modifier = Modifier,
    hasError: Boolean = false,
    otpCount: Int = 6,
    onTextChange: (String) -> Unit,
    onSubmit: (() -> Unit)? = null,
) {
    LaunchedEffect(Unit) {
        if (text.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }


    BasicTextField(
        modifier = Modifier
            .then(modifier)
        ,
        value = TextFieldValue(text, selection = TextRange(text.length)),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onTextChange(it.text)
            }
        },
        keyboardActions = KeyboardActions(
            onDone = {
                if (text.length == otpCount)
                    onSubmit?.invoke()
            }
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(otpCount) { index ->
                    OtpCharView(
                        index = index,
                        text = text,
                        hasError = hasError,
                        isFocused = index == text.length,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                }
            }
        }
    )
}

@Composable
private fun OtpCharView(
    index: Int,
    text: String,
    isFocused: Boolean,
    modifier: Modifier = Modifier,
    hasError: Boolean = false,
) {
    val borderColor = when {
        isFocused -> MaterialTheme.colorScheme.primary
        hasError -> MaterialTheme.colorScheme.error
        else -> Color.Transparent
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .border(
                1.dp,
                borderColor,
                RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)

    ) {
        Text(
            modifier = Modifier,
            text = (text.getOrNull(index) ?: "").toString(),
            textAlign = TextAlign.Center
        )
    }

}

