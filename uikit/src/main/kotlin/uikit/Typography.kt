package uikit

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

object TypographyKit {

    val bodyRegular = TextStyle(
        fontFamily = SFCompatFamily,
        lineHeight = 20.sp,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
    )
    val bodyMedium = TextStyle(
        fontFamily = SFCompatFamily,
        lineHeight = 20.sp,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,

    )
    val caption = TextStyle(
        fontFamily = SFCompatFamily,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,

    )
    val title = TextStyle(
        fontFamily = SFCompatFamily,
        lineHeight = 40.sp,
        fontSize = 40.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,
    )
    val action = TextStyle(
        fontFamily = SFCompatFamily,
        lineHeight = 17.sp,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        textDecoration = TextDecoration.Underline,
    )
}