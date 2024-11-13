package com.example.eventify.presentation.ui.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelectChip(
    text: String,
    selectedColor: Color = Color.Red
) {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        onClick = { selected = !selected },
        label = {
            Text(text = text)
        },
        selected = selected,
        leadingIcon = null,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = selectedColor
        )
    )
}


@Composable
fun ChipInfo(
    text: String,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    onClick: (() -> Unit)? = null
){
    AssistChip(
        onClick = onClick ?: {},
        shape = RoundedCornerShape(16.dp),
        colors = AssistChipDefaults.assistChipColors(
            containerColor = containerColor
        ),
        border = null,
        label = {
            Text(text = text, color = MaterialTheme.colorScheme.onPrimary)
        }
    )
}


@Composable
fun TagChip(
    text: String,
    onClick: (() -> Unit)? = null
) {
    AssistChip(
        onClick = onClick ?: {},
        shape = RoundedCornerShape(16.dp),
        border =  AssistChipDefaults.assistChipBorder(
            borderColor = MaterialTheme.colorScheme.primary
        ),
        label = {
            Text(text = text, color = MaterialTheme.colorScheme.primary)
        }
    )
}