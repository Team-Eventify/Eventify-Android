package uikit.components

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
import uikit.EventifyTheme
import uikit.TypographyKit


@Composable
fun CategorySelectChip(
    title: String,
    isSelected: Boolean = false,
    color: Color = MaterialTheme.colorScheme.primary,
    onSelect: () -> Unit,
) {
    FilterChip(
        onClick = onSelect,
        label = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary.takeIf { isSelected } ?: MaterialTheme.colorScheme.onSurface,
                style = TypographyKit.Info.tag,
            )
        },
        selected = isSelected,
        leadingIcon = null,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = color
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
                style = TypographyKit.Info.tag,
                modifier = Modifier
                    .padding(0.dp)
            )
        },
        modifier = Modifier
            .padding(0.dp)
            .then(modifier)
    )
}


@Composable
fun CategoryTagChip(
    text: String,
    color: Color,
    onClick: (() -> Unit)? = null
) {
    AssistChip(
        onClick = onClick ?: {},
        shape = RoundedCornerShape(16.dp),
        border = null,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = color
        ),
        label = {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                style = TypographyKit.Info.tag,
            )
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
            overflow = TextOverflow.Ellipsis,
            style = TypographyKit.Info.tag,
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
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier,
            style = TypographyKit.Info.tag,

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
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier,
            style = TypographyKit.Info.tag,

        )
    }
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
    CategoryTagChip(
        text = "Category",
        color = Color.Cyan
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