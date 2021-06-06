package jp.aoichaan0513.Kotlin_Utils

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


fun String.trim(l: Int, truncated: String = "…") =
    if (length > l) "${substring(0, l - truncated.length)}$truncated" else this


fun String.replace(map: Map<String, String>): String {
    var s = this

    for ((key, value) in map)
        s = s.replace(key, value)

    return s
}

fun String.replace(collection: Collection<Pair<String, String>>) = replace(collection.toMap())
fun String.replace(iterable: Iterable<Pair<String, String>>) = replace(iterable.toMap())
fun String.replace(vararg pairs: Pair<String, String>) = replace(pairs.toMap())


@Deprecated(
    "Misleading name. Replace `joinToString`.",
    ReplaceWith("this.joinToString(empty, separator, action)"),
    DeprecationLevel.ERROR
)
inline fun <T> Collection<T>.toString(action: (T) -> String, empty: String, separator: String = ", ") =
    joinToString(empty, separator, action)

@Deprecated(
    "Misleading name. Replace `joinToString`.",
    ReplaceWith("this.joinToString(empty, separator, action)"),
    DeprecationLevel.ERROR
)
inline fun <T> Iterable<T>.toString(action: (T) -> String, empty: String, separator: String = ", ") =
    joinToString(empty, separator, action)

@Deprecated(
    "Misleading name. Replace `joinToString`.",
    ReplaceWith("this.joinToString(empty, separator, action)"),
    DeprecationLevel.ERROR
)
inline fun <T> Array<T>.toString(action: (T) -> String, empty: String, separator: String = ", ") =
    joinToString(empty, separator, action)


inline fun <T> Collection<T>.joinToString(empty: String, separator: String = ", ", action: (T) -> String) =
    if (this.isNotEmpty()) {
        buildString {
            for (i in this@joinToString.indices)
                append("${action(this@joinToString.elementAt(i))}${if (i < this@joinToString.size - 1) separator else ""}")
        }.trim()
    } else {
        empty
    }

inline fun <T> Iterable<T>.joinToString(empty: String, separator: String = ", ", action: (T) -> String) =
    toList().joinToString(empty, separator, action)

inline fun <T> Array<T>.joinToString(empty: String, separator: String = ", ", action: (T) -> String) =
    toList().joinToString(empty, separator, action)