package core.common

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

fun String.toColorOrNull(): Color? = try {
    Color(toColorInt())
} catch (_: IllegalArgumentException) {
    null
}

// TODO chang this extension
fun String.toColor(fallback: Color) = toColorOrNull() ?: fallback