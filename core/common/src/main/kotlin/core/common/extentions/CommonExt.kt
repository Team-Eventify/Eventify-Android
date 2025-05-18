package core.common.extentions

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Any?.isNull(): Boolean = this == null

fun Any?.isNotNull(): Boolean = !this.isNull()

val Boolean?.safeBoolean: Boolean
    get() = this == true


fun String.redirect(context: Context) {
    context.startActivity(
        Intent(Intent.ACTION_VIEW, this.toUri())
    )
}