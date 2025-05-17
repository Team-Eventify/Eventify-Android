package uikit.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        lineHeight = 47.sp,
        fontSize = 40.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier, textAlign =
            textAlign)
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
