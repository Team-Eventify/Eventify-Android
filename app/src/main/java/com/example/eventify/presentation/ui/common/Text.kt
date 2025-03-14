package com.example.eventify.presentation.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(text = text, lineHeight = 47.sp, fontSize = 40.sp, fontWeight = FontWeight.SemiBold, modifier = modifier, textAlign = textAlign)
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
        fontSize = 15.sp,
        fontWeight = FontWeight.Light,
        lineHeight = 20.sp,
        textDecoration = textDecoration,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
    )
}

@Composable
fun BodyText(
    text: String,
    maxlines: Int = Int.MAX_VALUE,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        maxLines = maxlines,
        overflow = TextOverflow.Ellipsis,
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
        modifier = Modifier
            .clickable { onClick() }
            .then(modifier)
    )
}

@Composable
fun PrimaryButtonText(
    text: String,
    textDecoration: TextDecoration = TextDecoration.None,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        lineHeight = 22.sp,
        fontSize = 17.sp,
        fontWeight = FontWeight.Medium,
        textDecoration = textDecoration,
        modifier = Modifier
            .then(modifier)
    )
}


@Composable
fun EventCardTitle(
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onSecondary,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = TextAlign.Start,
        color = textColor,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .padding(0.dp, 5.dp)
    )
}


@Composable
fun HeadingText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        fontSize = 25.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .then(modifier)
    )
}

@Composable
fun SubHeadingText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .then(modifier)
    )
}

@Composable
fun ErrorInputText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier
            .then(modifier)
    )
}


@Composable
fun DisclaimerText(
    text: String,
    textDecoration: TextDecoration = TextDecoration.None,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        textDecoration = textDecoration,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onSecondary.copy(alpha = .4f),
        modifier = Modifier
            .then(modifier)
    )
}
