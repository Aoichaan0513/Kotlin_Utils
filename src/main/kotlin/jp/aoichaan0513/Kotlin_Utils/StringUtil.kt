package jp.aoichaan0513.Kotlin_Utils

inline val StringBuffer.trimString
    get() = toString().trim()

inline val StringBuilder.trimString
    get() = toString().trim()


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


@Deprecated("Misleading name. Replace `joinToString`.", ReplaceWith("this.joinToString(empty, separator, action)"))
inline fun <T> Collection<T>.toString(action: (T) -> String, empty: String, separator: String = ", ") =
    joinToString(empty, separator, action)

@Deprecated("Misleading name. Replace `joinToString`.", ReplaceWith("this.joinToString(empty, separator, action)"))
inline fun <T> Iterable<T>.toString(action: (T) -> String, empty: String, separator: String = ", ") =
    joinToString(empty, separator, action)

@Deprecated("Misleading name. Replace `joinToString`.", ReplaceWith("this.joinToString(empty, separator, action)"))
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