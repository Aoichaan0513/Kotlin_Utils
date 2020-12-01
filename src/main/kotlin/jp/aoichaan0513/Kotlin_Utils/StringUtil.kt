package jp.aoichaan0513.Kotlin_Utils

inline val StringBuffer.trimString
    get() = toString().trim()

inline val StringBuilder.trimString
    get() = toString().trim()

fun String.trim(l: Int, truncated: String = "…") = if (length > l) "${substring(0, l - 1)}$truncated" else this


fun String.replace(map: Map<String, String>): String {
    var s = this

    for ((key, value) in map)
        s = s.replace(key, value)

    return s
}

fun String.replace(collection: Collection<Pair<String, String>>) = replace(collection.toMap())
fun String.replace(vararg pairs: Pair<String, String>) = replace(pairs.asList())

@Deprecated("", ReplaceWith("String.replace(map)"))
fun String.replaceAll(map: Map<String, String>) = replace(map)

@Deprecated("", ReplaceWith("String.replace(collection)"))
fun String.replaceAll(collection: Collection<Pair<String, String>>) = replace(collection.toMap())

@Deprecated("", ReplaceWith("String.replace(pairs)"))
fun String.replaceAll(vararg pairs: Pair<String, String>) = replace(pairs.asList())

inline fun <T> Collection<T>.toString(action: (T) -> String, empty: String, separator: String = ", ") = if (this.isNotEmpty()) {
    buildString {
        for (i in this@toString.indices)
            append("${action(this@toString.elementAt(i))}${(i < this@toString.size - 1).getValue(separator, "")}")
    }.trim()
} else {
    empty
}

inline fun <T> Array<T>.toString(action: (T) -> String, empty: String, separator: String = ", ") =
        toList().toString(action, empty, separator)

inline fun <T> Iterable<T>.toString(action: (T) -> String, empty: String, separator: String = ", ") =
        toList().toString(action, empty, separator)
