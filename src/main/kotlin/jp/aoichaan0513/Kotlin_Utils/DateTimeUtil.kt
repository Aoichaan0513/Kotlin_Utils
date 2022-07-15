package jp.aoichaan0513.Kotlin_Utils

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Days
import org.joda.time.format.DateTimeFormat
import java.io.Serializable
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

/**
 * Get formatted datetime string.
 *
 * @param zoneId Timezone id (Optional, Default: [ZoneId.systemDefault])
 * @param pattern String for format (Optional, Default: "yyyy/MM/dd HH:mm:ss")
 *
 * @author Aoichaan0513
 */
fun DateTime.getFormattedDateTime(
    zoneId: ZoneId = DateTimeUtil.DEFAULT_ZONE_ID,
    pattern: String = DateTimeUtil.DEFAULT_PATTERN
) =
    DateTimeFormat.forPattern(pattern).withZone(DateTimeZone.forID(zoneId.id)).print(this)

/**
 * Get formatted datetime string.
 *
 * @param zoneId Timezone id (Optional, Default: [ZoneId.systemDefault])
 * @param pattern String for format (Optional, Default: "yyyy/MM/dd HH:mm:ss")
 *
 * @author Aoichaan0513
 */
fun LocalDate.getFormattedDateTime(
    zoneId: ZoneId = DateTimeUtil.DEFAULT_ZONE_ID,
    pattern: String = DateTimeUtil.DEFAULT_PATTERN
) =
    atStartOfDay(zoneId).getFormattedDateTime(zoneId, pattern)

/**
 * Get formatted datetime string.
 *
 * @param zoneId Timezone id (Optional, Default: [ZoneId.systemDefault])
 * @param pattern String for format (Optional, Default: "yyyy/MM/dd HH:mm:ss")
 *
 * @author Aoichaan0513
 */
fun LocalDateTime.getFormattedDateTime(
    zoneId: ZoneId = DateTimeUtil.DEFAULT_ZONE_ID,
    pattern: String = DateTimeUtil.DEFAULT_PATTERN
) =
    atZone(zoneId).getFormattedDateTime(zoneId, pattern)

/**
 * Get formatted datetime string.
 *
 * @param zoneId Timezone id (Optional, Default: [ZoneId.systemDefault])
 * @param pattern String for format (Optional, Default: "yyyy/MM/dd HH:mm:ss")
 *
 * @author Aoichaan0513
 */
fun OffsetDateTime.getFormattedDateTime(
    zoneId: ZoneId = DateTimeUtil.DEFAULT_ZONE_ID,
    pattern: String = DateTimeUtil.DEFAULT_PATTERN
) =
    atZoneSameInstant(zoneId).getFormattedDateTime(zoneId, pattern)

/**
 * Get formatted datetime string.
 *
 * @param zoneId Timezone id (Optional, Default: [ZoneId.systemDefault])
 * @param pattern String for format (Optional, Default: "yyyy/MM/dd HH:mm:ss")
 *
 * @author Aoichaan0513
 */
fun ZonedDateTime.getFormattedDateTime(
    zoneId: ZoneId = DateTimeUtil.DEFAULT_ZONE_ID,
    pattern: String = DateTimeUtil.DEFAULT_PATTERN
) =
    withZoneSameInstant(zoneId).format(DateTimeFormatter.ofPattern(pattern))


/**
 * Get datetime difference.
 *
 * @param date Datetime for comparison (Optional, Default: [Date])
 *
 * @author Aoichaan0513
 */
fun Date.getDifference(date: Date = Date()) =
    (date.time - this.time) / 86400000L

/**
 * Get datetime difference.
 *
 * @param dateTime Datetime for comparison (Optional, Default: [DateTime.now])
 *
 * @author Aoichaan0513
 */
fun DateTime.getDifference(dateTime: DateTime = DateTime.now()) =
    Days.daysBetween(this, dateTime).days.toLong()

/**
 * Get datetime difference.
 *
 * @param localDate Datetime for comparison (Optional, Default: [LocalDate.now])
 * @param chronoUnit Object for comparison (Optional, Default: [ChronoUnit.DAYS])
 *
 * @author Aoichaan0513
 */
fun LocalDate.getDifference(localDate: LocalDate = LocalDate.now(), chronoUnit: ChronoUnit = ChronoUnit.DAYS) =
    chronoUnit.between(this, localDate)

/**
 * Get datetime difference.
 *
 * @param localDateTime Datetime for comparison (Optional, Default: [LocalDateTime.now])
 * @param chronoUnit Object for comparison (Optional, Default: [ChronoUnit.DAYS])
 *
 * @author Aoichaan0513
 */
fun LocalDateTime.getDifference(
    localDateTime: LocalDateTime = LocalDateTime.now(),
    chronoUnit: ChronoUnit = ChronoUnit.DAYS
) =
    chronoUnit.between(this, localDateTime)

/**
 * Get datetime difference.
 *
 * @param offsetDateTime Datetime for comparison (Optional, Default: [OffsetDateTime.now])
 * @param chronoUnit Object for comparison (Optional, Default: [ChronoUnit.DAYS])
 *
 * @author Aoichaan0513
 */
fun OffsetDateTime.getDifference(
    offsetDateTime: OffsetDateTime = OffsetDateTime.now(),
    chronoUnit: ChronoUnit = ChronoUnit.DAYS
) =
    chronoUnit.between(this, offsetDateTime)

/**
 * Get datetime difference.
 *
 * @param zonedDateTime Datetime for comparison (Optional, Default: [ZonedDateTime.now])
 * @param chronoUnit Object for comparison (Optional, Default: [ChronoUnit.DAYS])
 *
 * @author Aoichaan0513
 */
fun ZonedDateTime.getDifference(
    zonedDateTime: ZonedDateTime = ZonedDateTime.now(),
    chronoUnit: ChronoUnit = ChronoUnit.DAYS
) =
    chronoUnit.between(this, zonedDateTime)


class DateTimeUtil {

    companion object {

        val DEFAULT_ZONE_ID
            get() = ZoneId.systemDefault()
        var DEFAULT_PATTERN = "yyyy/MM/dd HH:mm:ss"

        fun formatTimestamp(l: Long, isMilliSeconds: Boolean = false) = Timestamp(
            if (isMilliSeconds) l / (1000 * 60 * 60) % 24 else l / 3600,
            if (isMilliSeconds) l / (1000 * 60) % 60 else (l % 3600) / 60,
            if (isMilliSeconds) l / 1000 % 60 else l % 60
        )

        fun formatTimestamp(i: Int, isMilliSeconds: Boolean = false) = formatTimestamp(i.toLong(), isMilliSeconds)
    }

    data class Timestamp(val rawHours: Int, val rawMinutes: Int, val rawSeconds: Int) : Serializable {

        val hours = format(rawHours)
        val minutes = format(rawMinutes)
        val seconds = format(rawSeconds)

        val toNormal = "${if (rawHours > 0) "$hours:" else ""}$minutes:$seconds"
        val toJapan = "${if (rawHours > 0) "${hours}時間" else ""}${minutes}分${seconds}秒"

        constructor(rawHours: Long, rawMinutes: Long, rawSeconds: Long) :
                this(rawHours.toInt(), rawMinutes.toInt(), rawSeconds.toInt())

        private fun format(i: Int) = if (i < 10) "0$i" else i.toString()
    }
}