package uikit

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

//object TypographyKit {
//
//    val bodyRegular = TextStyle(
//        fontFamily = SFCompatFamily,
//        lineHeight = 20.sp,
//        fontSize = 16.sp,
//        fontWeight = FontWeight.Normal,
//        letterSpacing = 0.sp,
//    )
//    val bodyMedium = TextStyle(
//        fontFamily = SFCompatFamily,
//        lineHeight = 20.sp,
//        fontSize = 20.sp,
//        fontWeight = FontWeight.Medium,
//        letterSpacing = 0.sp,
//
//    )
//    val caption = TextStyle(
//        fontFamily = SFCompatFamily,
//        lineHeight = 20.sp,
//        fontSize = 14.sp,
//        fontWeight = FontWeight.Medium,
//        letterSpacing = 0.sp,
//
//    )
//    val title = TextStyle(
//        fontFamily = SFCompatFamily,
//        lineHeight = 40.sp,
//        fontSize = 40.sp,
//        fontWeight = FontWeight.Medium,
//        letterSpacing = 0.sp,
//    )
//    val action = TextStyle(
//        fontFamily = SFCompatFamily,
//        lineHeight = 17.sp,
//        fontSize = 16.sp,
//        fontWeight = FontWeight.Normal,
//        textDecoration = TextDecoration.Underline,
//    )
//}

object TypographyKit {
    object Heading {
        val xlarge = TextStyle(
            fontFamily = SFCompatFamily,
            lineHeight = 40.sp,
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.sp,
        )
        val large = TextStyle(
            fontFamily = SFCompatFamily,
            lineHeight = 20.sp,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp,
        )
        val medium = TextStyle(
            fontFamily = SFCompatFamily,
            lineHeight = 20.sp,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.sp,
        )
    }

    object Body {
        val regular = TextStyle(
            fontFamily = SFCompatFamily,
            lineHeight = 20.sp,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp,
        )
        val small = TextStyle(
            fontFamily = SFCompatFamily,
            lineHeight = 20.sp,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp,
        )
        val xsmall = TextStyle(
            fontFamily = SFCompatFamily,
            lineHeight = 20.sp,
            fontSize = 12.sp,
            fontWeight = FontWeight.Thin,
            letterSpacing = 0.sp,
        )
    }

    object Label {
        val large = TextStyle(
            fontFamily = SFCompatFamily,
            lineHeight = 12.sp,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp,
        )
        val medium = TextStyle(
            fontFamily = SFCompatFamily,
            lineHeight = 22.sp,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.sp,
        )
        val small = TextStyle(
            fontFamily = SFCompatFamily,
            lineHeight = 20.sp,
            fontSize = 10.sp,
            fontWeight = FontWeight.Thin,
            letterSpacing = 0.sp,
        )
    }

    object Info {
        val tag = TextStyle(
            fontFamily = SFCompatFamily,
            lineHeight = 16.sp,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.sp,
        )

        val Small = TextStyle(
            fontFamily = SFCompatFamily,
            lineHeight = 10.sp,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp,
        )
    }
}
