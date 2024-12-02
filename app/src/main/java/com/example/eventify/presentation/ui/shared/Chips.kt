package com.example.eventify.presentation.ui.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.presentation.models.CategorySelectItem


val CATEGORY_SELECT_COLORS = listOf(
    Color(0xFFFFB3A7),
    Color(0xFFA3C7FF),
    Color(0xFFA3D5FF),
    Color(0xFFFFCCE7),
    Color(0xFFFFF4A3),
    Color(0xFFC6A4E7),
    Color(0xFFFFD8B1),
    Color(0xFFD3E4C4),
    Color(0xFFFFCEF3),
    Color(0xFFC6A4E7),
    Color(0xFFFFE4B3),
    Color(0xFFFFF7E1)
)

@Composable
fun CategorySelectChip(
    category: CategorySelectItem,
    onSelect: (String) -> Unit,
    selectedColor: Color? = null
) {
    FilterChip(
        onClick = {
            onSelect(
                category.id
            )
        },
        label = {
            Text(text = category.title)
        },
        selected = category.selected,
        leadingIcon = null,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = selectedColor ?: CATEGORY_SELECT_COLORS.random()
        )
    )
}


@Composable
fun ChipInfo(
    text: String,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
){
    AssistChip(
        onClick = onClick ?: {},
        shape = RoundedCornerShape(16.dp),
        colors = AssistChipDefaults.assistChipColors(
            containerColor = containerColor
        ),
        border = null,
        label = {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(0.dp)
            )
        },
        modifier = Modifier
            .padding(0.dp)
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
        border = AssistChipDefaults.assistChipBorder(enabled = true, borderColor = MaterialTheme.colorScheme.primary),
        label = {
            Text(text = text, color = MaterialTheme.colorScheme.primary)
        }
    )
}

@Composable
fun EventInfoChip(
    text: String,
    onClick: (() -> Unit)? = null
) {
    AssistChip(
        onClick = onClick ?: {},
        modifier = Modifier.padding(0.dp),
        shape = RoundedCornerShape(16.dp),
        border = AssistChipDefaults.assistChipBorder(enabled = true, borderColor = Color.White),
        label = {
            Text(text = text, color = Color.White, modifier = Modifier.padding(0.dp))
        }
    )
}

@Composable
fun UpComingEventInfoChip(
    text: String,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
        )
    }
}


@Composable
fun FinishedEventInfoChip(
    text: String,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .background(Color(0xFF858591), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
        )
    }
}


@Preview(name = "CategorySelectChip")
@Composable
private fun PreviewCategorySelectChip() {
    CategorySelectChip(
        CategorySelectItem(
            id = "",
            title = "Backend",
            selected = false
        ),
        {_->}
    )
}

@Preview(name = "ChipInfo")
@Composable
private fun PreviewChipInfo() {
    ChipInfo(
        text = "Category"
    )
}


@Preview(name = "TagChip")
@Composable
private fun PreviewTagChip() {
    TagChip(
        text = "Category"
    )
}

@Preview(name = "EventInfoChip")
@Composable
private fun PreviewEventInfoChip() {
    EventInfoChip(
        text = "Category"
    )
}

@Preview(name = "UpComingEventInfoChip")
@Composable
private fun PreviewUpComingEventInfoChip() {
    UpComingEventInfoChip(
        text = "Category"
    )
}


@Preview(name = "FinishedEventInfoChip")
@Composable
private fun PreviewFinishedEventInfoChip() {
    FinishedEventInfoChip(
        text = "Category"
    )
}