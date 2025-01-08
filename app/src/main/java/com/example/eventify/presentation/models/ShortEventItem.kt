package com.example.eventify.presentation.models

import com.example.eventify.presentation.utils.asDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.annotation.concurrent.Immutable


const val SHORT_DATE_FORMAT = "dd MMM"
const val SHORT_TIME_FORMAT = "HH:mm"

const val LONG_DATE_FORMAT = "dd MMMM"
const val LONG_TIME_FORMAT = "HH:mm"

@Immutable
data class ShortEventItem(
    val id: String,
    val title: String,
    val description: String,
    val cover: String = "",
    val start: Int,
    val end: Int,
    val location: String
){
    val duration: String
        get() {
            val st = start.asDate(short = true)
            val ed = end.asDate(short = true)
            return listOf(st, ed).joinToString(" - ")
        }
}