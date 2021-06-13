package jp.aoichaan0513.Kotlin_Utils

@Deprecated("Deprecated method.", ReplaceWith("if (this) { v1 } else { v2 }"), DeprecationLevel.ERROR)
fun <T> Boolean.getValue(v1: T, v2: T): T = if (this) v1 else v2