package com.example.eventify.presentation.models

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale

data class ShortEventItem(
    val id: String,
    val title: String,
    val description: String,
    val start: Int,
    val end: Int
)