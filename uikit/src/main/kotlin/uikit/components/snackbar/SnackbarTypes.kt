package uikit.components.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.eventify.uikit.R as UiKitR

data class SnackbarColors(
    val containerColor: Color,
    val textColor: Color,
    val iconTint: Color,
) {
    companion object {
        @Composable
        fun default() = SnackbarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            textColor = MaterialTheme.colorScheme.onSurface,
            iconTint = MaterialTheme.colorScheme.primary,
        )
    }
}


sealed interface SnackbarStyle {

    val iconResId: Int?

    @Composable
    fun colors(): SnackbarColors

    data class Success(
        override val iconResId: Int? = UiKitR.drawable.xmark_circle_fill
    ) : SnackbarStyle {

        @Composable
        override fun colors(): SnackbarColors = SnackbarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            textColor = MaterialTheme.colorScheme.onSurface,
            iconTint = MaterialTheme.colorScheme.primary,
        )
    }

    data object Error : SnackbarStyle {
        @DrawableRes override val iconResId: Int? = UiKitR.drawable.ic_error

        @Composable
        override fun colors(): SnackbarColors = SnackbarColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            textColor = MaterialTheme.colorScheme.onSurface,
            iconTint = MaterialTheme.colorScheme.onSurface,
        )

    }

}


