package domain.models

import androidx.annotation.StringRes

data class LogoIcon(
    @StringRes val titleRes: Int,
    val icon: Int,
    val alias: String
)