package jp.aoichaan0513.Kotlin_Utils

val StringBuffer.trimString
    get() = toString().trim()

val StringBuilder.trimString
    get() = toString().trim()

fun String.trim(l: Int, truncated: String = "…"): String {
    return if (length > l) "${substring(0, l - 1)}$truncated" else this
}

fun String.replaceAll(map: Map<String, String>): String {
    var s = this

    for ((key, value) in map)
        s = s.replace(key, value)

    return s
}

fun String.replaceAll(collection: Collection<Pair<String, String>>): String {
    var s = this

    for ((key, value) in collection)
        s = s.replace(key, value)

    return s
}

fun String.replaceAll(vararg pairs: Pair<String, String>): String {
    return replaceAll(pairs.asList())
}

fun <T> Array<T>.toString(action: (T) -> String, empty: String, separator: String = ", "): String {
    return this.toList().toString(action, empty, separator)
}

fun <T> Iterable<T>.toString(action: (T) -> String, empty: String, separator: String = ", "): String {
    return this.toList().toString(action, empty, separator)
}

fun <T> Collection<T>.toString(action: (T) -> String, empty: String, separator: String = ", "): String {
    val stringBuilder = StringBuilder()
    if (this.isEmpty()) return empty
    for (i in this.indices)
        stringBuilder.append("${action(this.elementAt(i))}${(i < this.size - 1).getValue(separator, "")}")
    return stringBuilder.trimString
}
