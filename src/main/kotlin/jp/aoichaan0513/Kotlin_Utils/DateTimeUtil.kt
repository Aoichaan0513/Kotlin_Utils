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


fun DateTime.getFormattedDateTime(zoneId: ZoneId) = DateTimeFormat.mediumDateTime().withZone(DateTimeZone.forID(zoneId.id)).print(this)
fun LocalDate.getFormattedDateTime(zoneId: ZoneId) = atStartOfDay(zoneId).getFormattedDateTime()
fun LocalDateTime.getFormattedDateTime(zoneId: ZoneId) = atZone(zoneId).getFormattedDateTime()
fun OffsetDateTime.getFormattedDateTime(zoneId: ZoneId) = atZoneSameInstant(zoneId).getFormattedDateTime()
fun ZonedDateTime.getFormattedDateTime() = format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))

fun Date.getDifference(date: Date = Date()) = (date.time - this.time) / 86400000L
fun DateTime.getDifference(dateTime: DateTime = DateTime.now()) = Days.daysBetween(this, dateTime).days.toLong()
fun LocalDate.getDifference(localDate: LocalDate = LocalDate.now(), chronoUnit: ChronoUnit = ChronoUnit.DAYS) = chronoUnit.between(this, localDate)
fun LocalDateTime.getDifference(localDateTime: LocalDateTime = LocalDateTime.now(), chronoUnit: ChronoUnit = ChronoUnit.DAYS) = chronoUnit.between(this, localDateTime)
fun OffsetDateTime.getDifference(offsetDateTime: OffsetDateTime = OffsetDateTime.now(), chronoUnit: ChronoUnit = ChronoUnit.DAYS) = chronoUnit.between(this, offsetDateTime)
fun ZonedDateTime.getDifference(zonedDateTime: ZonedDateTime = ZonedDateTime.now(), chronoUnit: ChronoUnit = ChronoUnit.DAYS) = chronoUnit.between(this, zonedDateTime)


class DateTimeUtil {

    companion object {
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

        constructor(rawHours: Long, rawMinutes: Long, rawSeconds: Long) : this(rawHours.toInt(), rawMinutes.toInt(), rawSeconds.toInt())

        private fun format(i: Int) = if (i < 10) "0$i" else i.toString()
    }
}