package com.example.eventify.presentation.ui.shared

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        modifier = modifier
    )
}

@Composable
fun ActionText(
    text: String,
    onClick: () -> Unit,
    textAlign: TextAlign = TextAlign.Left,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 17.sp,
        textAlign = textAlign,
        modifier = modifier
            .clickable { onClick() }
    )
}

@Composable
fun ActionPrimaryText(
    text: String,
    onClick: () -> Unit,
    textAlign: TextAlign = TextAlign.Left,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 17.sp,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline,
        modifier = modifier
            .clickable { onClick() }
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
        textDecoration = textDecoration,
    )
}


@Composable
fun EventCardTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        modifier = modifier
            .padding(0.dp, 5.dp)
    )
}


@Composable
fun HeadingText(
    text: String
) {
    Text(
        text = text,
        fontSize = 25.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun SubHeadingText(
    text: String
) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium
    )
}


@Composable
fun ShortenedBodyText(
    text: String,
    textState: Boolean,
    maxLines: Int = 10,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        maxLines = if (textState) maxLines else Int.MAX_VALUE,
        overflow = if (textState) TextOverflow.Ellipsis else TextOverflow.Clip,
        modifier = modifier
            .animateContentSize()
    )
}