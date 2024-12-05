package com.example.eventify.presentation.models

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


const val SHORT_DATE_FORMAT = "dd MMM"
const val SHORT_TIME_FORMAT = "HH:mm"

const val LONG_DATE_FORMAT = "dd MMMM"
const val LONG_TIME_FORMAT = "HH:mm"

data class ShortEventItem(
    val id: String,
    val title: String,
    val description: String,
    val start: Int,
    val end: Int
){
    val localDateTimeStart: LocalDateTime
        get() = LocalDateTime.ofEpochSecond(start.toLong(), 0, ZoneOffset.UTC)

    val localDateTimeEnd: LocalDateTime
        get() = LocalDateTime.ofEpochSecond(end.toLong(), 0, ZoneOffset.UTC)

}

fun LocalDateTime.time(short: Boolean = true): String {
    val pattern = if (short) SHORT_TIME_FORMAT else LONG_TIME_FORMAT
    val formater = DateTimeFormatter.ofPattern(pattern)
    return this.format(formater)
}

fun LocalDateTime.date(short: Boolean = true): String {
    val pattern = if (short) SHORT_DATE_FORMAT else LONG_DATE_FORMAT
    val formater = DateTimeFormatter.ofPattern(pattern)
    return this.format(formater)
}
