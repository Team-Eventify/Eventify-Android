package core.common.extentions

fun Any?.isNull(): Boolean = this == null

fun Any?.isNotNull(): Boolean = !this.isNull()

val Boolean?.safeBoolean: Boolean
    get() = this == true