package com.example.eventify.presentation.utils

import com.example.eventify.presentation.models.LONG_DATE_FORMAT
import com.example.eventify.presentation.models.LONG_TIME_FORMAT
import com.example.eventify.presentation.models.SHORT_DATE_FORMAT
import com.example.eventify.presentation.models.SHORT_TIME_FORMAT
import kotlinx.serialization.json.internal.decodeStringToJsonTree
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


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
