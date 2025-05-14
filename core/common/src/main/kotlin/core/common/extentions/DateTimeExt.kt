package core.common.extentions

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

const val SHORT_DATE_FORMAT = "dd MMM"
const val SHORT_TIME_FORMAT = "HH:mm"

const val LONG_DATE_FORMAT = "dd MMMM"
const val LONG_TIME_FORMAT = "HH:mm"


fun Long.toLocalDateTime(): LocalDateTime = LocalDateTime.ofEpochSecond(this, 0, ZoneOffset.UTC)


fun LocalDateTime.asDate(short: Boolean = true): String {
    val pattern = if (short) SHORT_DATE_FORMAT else LONG_DATE_FORMAT
    val formater = DateTimeFormatter.ofPattern(pattern)
    return this.format(formater)
}


fun LocalDateTime.asTime(short: Boolean = true): String {
    val pattern = if (short) SHORT_TIME_FORMAT else LONG_TIME_FORMAT
    val formater = DateTimeFormatter.ofPattern(pattern)
    return this.format(formater)
}

fun Long.asDate(short: Boolean = true) = this.toLocalDateTime().asDate(short)

fun Long.asTime(short: Boolean = true) = this.toLocalDateTime().asTime(short)
