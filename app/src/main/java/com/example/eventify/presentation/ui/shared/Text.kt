package com.example.eventify.presentation.ui.shared

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp


@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(text = text, lineHeight = 47.sp, fontSize = 40.sp, fontWeight = FontWeight.SemiBold, modifier = modifier)
}


@Composable
fun AnnotationText(
    text: String,
    textDecoration: TextDecoration = TextDecoration.None,
    textAlign: TextAlign = TextAlign.Left,
    modifier: Modifier = Modifier,

) {
    Text(
        text = text,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 17.sp,
        textDecoration = textDecoration,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
    )
}

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 17.sp,
        modifier = modifier
    )
}

@Composable
fun PrimaryButtonText(
    text: String,
    textDecoration: TextDecoration = TextDecoration.None
) {
    Text(
        text = text,
        lineHeight = 22.sp,
        fontSize = 17.sp,
        fontWeight = FontWeight.Medium,
        textDecoration = textDecoration
    )

}