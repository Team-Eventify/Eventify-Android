package com.example.eventify.presentation.models

import com.example.eventify.domain.models.EventState
import com.example.eventify.presentation.utils.asDate
import com.example.eventify.presentation.utils.toLocalDateTime
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.annotation.concurrent.Immutable
import kotlin.time.toKotlinDuration


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
    val start: Long,
    val state: EventState,
    val end: Long,
    val location: String
){
    val duration: String
        get() {
            val startTime = start.toLocalDateTime()
            val endTime = end.toLocalDateTime()

            val duration = Duration.between(startTime, endTime)

            if (duration.toDays() <= 1)
                return startTime.asDate()

            return listOf(startTime.asDate(), endTime.asDate()).joinToString(" - ")
        }
}