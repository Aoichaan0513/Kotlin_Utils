package jp.aoichaan0513.kotlin_utils

import java.math.BigDecimal
import java.math.BigInteger

val String.isByte
    get() = toByteOrNull() != null

val String.isShort
    get() = toShortOrNull() != null

val String.isInt
    get() = toIntOrNull() != null

val String.isLong
    get() = toLongOrNull() != null

val String.isFloat
    get() = toFloatOrNull() != null

val String.isDouble
    get() = toDoubleOrNull() != null


val String.isUByte
    get() = toUByteOrNull() != null

val String.isUShort
    get() = toUShortOrNull() != null

val String.isUInt
    get() = toUIntOrNull() != null

val String.isULong
    get() = toULongOrNull() != null


val String.isBigInteger
    get() = toBigIntegerOrNull() != null

val String.isBigDecimal
    get() = toBigDecimalOrNull() != null


inline val StringBuffer.trimString
    get() = toString().trim()

inline val StringBuilder.trimString
    get() = toString().trim()


fun String.toByte(defaultValue: Byte = 0) = toByteOrNull() ?: defaultValue

fun String.toShort(defaultValue: Short = 0) = toShortOrNull() ?: defaultValue

fun String.toInt(defaultValue: Int = 0) = toIntOrNull() ?: defaultValue

fun String.toLong(defaultValue: Long = 0) = toLongOrNull() ?: defaultValue

fun String.toFloat(defaultValue: Float = 0.0F) = toFloatOrNull() ?: defaultValue

fun String.toDouble(defaultValue: Double = 0.0) = toDoubleOrNull() ?: defaultValue


fun String.toUByte(defaultValue: UByte = 0U) = toUByteOrNull() ?: defaultValue

fun String.toUShort(defaultValue: UShort = 0U) = toUShortOrNull() ?: defaultValue

fun String.toUInt(defaultValue: UInt = 0U) = toUIntOrNull() ?: defaultValue

fun String.toULong(defaultValue: ULong = 0U) = toULongOrNull() ?: defaultValue


fun String.toBigInteger(defaultValue: BigInteger = 0.toBigInteger()) = toBigIntegerOrNull() ?: defaultValue

fun String.toBigDecimal(defaultValue: BigDecimal = 0.0.toBigDecimal()) = toBigDecimalOrNull() ?: defaultValue

fun String.ansi() = Ansi(this)


fun String.truncate(length: Int, separator: String? = null, truncated: String = "…") = if (this.length > length) {
    val text = substring(0, length - truncated.length)
    if (!separator.isNullOrBlank()) {
        "${substring(0, text.lastIndexOf(separator)).trim()}$truncated"
    } else {
        "$text$truncated"
    }
} else {
    this
}


fun String.replace(map: Map<String, String?>): String {
    var s = this

    for ((key, value) in map.filterValues { !it.isNullOrBlank() })
        s = s.replace(key, value!!)

    return s
}

fun String.replace(collection: Collection<Pair<String, String?>?>): String {
    var s = this

    for ((key, value) in collection.filterNotNull().filter { !it.second.isNullOrBlank() })
        s = s.replace(key, value!!)

    return s
}

fun String.replace(iterable: Iterable<Pair<String, String?>?>) = replace(iterable.toList())
fun String.replace(vararg pairs: Pair<String, String?>?) = replace(pairs.toList())


fun <T> Collection<T>.joinToString(empty: String, separator: String = ", ", action: ((T) -> String)? = null) =
    if (this.isNotEmpty()) {
        buildString {
            for ((index, element) in this@joinToString.withIndex()) {
                append(
                    when {
                        action != null -> action(element)
                        element is CharSequence? || element is Char -> element
                        else -> element.toString()
                    }
                )
                if (index < this@joinToString.size - 1)
                    append(separator)
            }
        }.trim()
    } else {
        empty
    }

fun <T> Iterable<T>.joinToString(empty: String, separator: String = ", ", action: ((T) -> String)? = null) =
    toList().joinToString(empty, separator, action)

fun <T> Array<T>.joinToString(empty: String, separator: String = ", ", action: ((T) -> String)? = null) =
    toList().joinToString(empty, separator, action)


fun <T> Collection<T>.joinToStringTrim(
    length: Int,
    empty: String,
    separator: String = ", ",
    truncated: String = "…",
    action: (T) -> String
) = joinToString(empty, separator, action).let {
    if (it.length > length) {
        val l = it.substring(0, length - truncated.length).lastIndexOf(separator.trim())
        "${it.substring(0, l).trim()}$truncated"
    } else {
        it
    }
}

fun <T> Iterable<T>.joinToStringTrim(
    length: Int,
    empty: String,
    separator: String = ", ",
    truncated: String = "…",
    action: (T) -> String
) = toList().joinToStringTrim(length, empty, separator, truncated, action)

fun <T> Array<T>.joinToStringTrim(
    length: Int,
    empty: String,
    separator: String = ", ",
    truncated: String = "…",
    action: (T) -> String
) = toList().joinToStringTrim(length, empty, separator, truncated, action)