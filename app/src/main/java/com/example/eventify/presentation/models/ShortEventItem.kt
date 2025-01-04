package com.example.eventify.presentation.models

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
    val end: Int
)