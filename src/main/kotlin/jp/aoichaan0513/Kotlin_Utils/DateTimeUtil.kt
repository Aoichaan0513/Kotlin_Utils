package jp.aoichaan0513.Kotlin_Utils

import java.io.Serializable

class DateTimeUtil {
    companion object {

        fun formatTimestamp(i: Int, isMilliSeconds: Boolean = false): Timestamp {
            return formatTimestamp(i.toLong(), isMilliSeconds)
        }

        fun formatTimestamp(l: Long, isMilliSeconds: Boolean = false): Timestamp {
            return Timestamp(
                if (isMilliSeconds) l / (1000 * 60 * 60) % 24 else l / 3600,
                if (isMilliSeconds) l / (1000 * 60) % 60 else (l % 3600) / 60,
                if (isMilliSeconds) l / 1000 % 60 else l % 60
            )
        }
    }

    data class Timestamp(val rawHours: Int, val rawMinutes: Int, val rawSeconds: Int) : Serializable {

        val hours = format(rawHours)
        val minutes = format(rawMinutes)
        val seconds = format(rawSeconds)

        val toNormal = "${if (rawHours > 0) "$hours:" else ""}$minutes:$seconds"
        val toJapan = "${if (rawHours > 0) "${hours}時間" else ""}${minutes}分${seconds}秒"

        constructor(rawHours: Long, rawMinutes: Long, rawSeconds: Long) : this(
            rawHours.toInt(),
            rawMinutes.toInt(),
            rawSeconds.toInt()
        )

        private fun format(i: Int): String {
            return if (i < 10) "0$i" else i.toString()
        }
    }
}