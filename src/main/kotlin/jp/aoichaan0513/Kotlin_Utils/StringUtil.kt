package jp.aoichaan0513.Kotlin_Utils

val StringBuffer.trimString
    get() = toString().trim()

val StringBuilder.trimString
    get() = toString().trim()

fun String.trim(l: Int, truncated: String = "…") = if (length > l) "${substring(0, l - 1)}$truncated" else this


fun String.replaceAll(map: Map<String, String>): String {
    var s = this

    for ((key, value) in map)
        s = s.replace(key, value)

    return s
}

fun String.replaceAll(collection: Collection<Pair<String, String>>) = replaceAll(collection.toMap())
fun String.replaceAll(vararg pairs: Pair<String, String>) = replaceAll(pairs.asList())


fun <T> Collection<T>.toString(action: (T) -> String, empty: String, separator: String = ", ") = if (this.isNotEmpty()) {
    buildString {
        for (i in this@toString.indices)
            append("${action(this@toString.elementAt(i))}${(i < this@toString.size - 1).getValue(separator, "")}")
    }.trim()
} else {
    empty
}

fun <T> Array<T>.toString(action: (T) -> String, empty: String, separator: String = ", ") =
    toList().toString(action, empty, separator)

fun <T> Iterable<T>.toString(action: (T) -> String, empty: String, separator: String = ", ") =
    toList().toString(action, empty, separator)
