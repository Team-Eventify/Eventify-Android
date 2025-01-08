package com.example.eventify.presentation.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.ui.theme.EventifyTheme
import kotlin.math.max


@Composable
fun CategorySelectChip(
    category: CategorySelectItem,
    onSelect: (String, Boolean) -> Unit,
) {
    FilterChip(
        onClick = {
            onSelect(category.id, !category.selected)
        },
        label = {
            Text(
                text = category.title,
                color = if (category.selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
        },
        selected = category.selected,
        leadingIcon = null,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = category.color
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
fun EventInfoTag(
    text: String,
) {
    Box(
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(16.dp))
            .padding(horizontal = 10.dp, vertical = 2.dp)

    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

}

@Composable
fun UpComingEventInfoTag(
    text: String
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
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
        )
    }
}


@Composable
fun FinishedEventInfoChip(
    text: String,
) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
            selected = true,
            color = Color.Cyan
        ),
        {_, _->}
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
    EventifyTheme(darkTheme = true) {
        Surface {
            EventInfoTag(
                text = "Category"
            )
        }
    }
}

@Preview(name = "UpComingEventInfoChip")
@Composable
private fun PreviewUpComingEventInfoChip() {
    UpComingEventInfoTag(
        text = "Category"
    )
}


@Preview(name = "FinishedEventInfoChip")
@Composable
private fun PreviewFinishedEventInfoChip() {
    EventifyTheme(darkTheme = true) {
        FinishedEventInfoChip(
            text = "Category"
        )
    }
}