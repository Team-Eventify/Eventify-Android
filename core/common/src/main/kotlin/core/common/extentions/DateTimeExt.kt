package core.common.extentions

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


enum class DateTimePattern(val value: String) {
    FullDateTime("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),

    FullNumericDate("dd-MM-yyyy"),

    ShortNamedDate("dd MMM"),

    ShortDate("dd MM"),

    ShortTime("HH:mm"),

}




fun Long.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofEpochSecond(this, 0, ZoneOffset.UTC)
}


fun LocalDateTime.format(
    pattern: DateTimePattern,
    locale: Locale = Locale.getDefault(),
) : String {
    return this.format(
        DateTimeFormatter.ofPattern(
            pattern.value,
            locale,
        )
    )
}

fun LocalDateTime.utcFormat(
    pattern: DateTimePattern,
    zoneId: ZoneId = ZoneId.systemDefault(),
    locale: Locale = Locale.getDefault(),
) : String {
    return this
        .toZonedDateTime(zoneId.id)
        .format(
            DateTimeFormatter.ofPattern(
                pattern.value,
                locale,
            )
        )
}


fun LocalDateTime.toZonedDateTime(
    zoneId: String = "Europe/Moscow",
): ZonedDateTime {
    return this
        .atOffset(ZoneOffset.UTC)
        .toZonedDateTime()
        .withZoneSameInstant(
            ZoneId.of(zoneId)
        )
}

fun Long.toUtcFormat(
    pattern: DateTimePattern,
    zoneId: ZoneId = ZoneId.systemDefault(),
    locale: Locale = Locale.getDefault(),
) : String {
    return this
        .toLocalDateTime()
        .utcFormat(
            pattern = pattern,
            locale = locale,
            zoneId = zoneId
        )

}

fun durationUtcFormatted(
    start: Long,
    end: Long,
) : String {
    val startTime = start.toLocalDateTime()
    val endTime = end.toLocalDateTime()

    val duration = Duration.between(startTime, endTime)

    val pattern = when {
        duration.toDays() <= 1 -> DateTimePattern.ShortTime
        else -> DateTimePattern.ShortTime
    }
    return "${startTime.utcFormat(pattern)} - ${endTime.utcFormat(pattern)}"
}
