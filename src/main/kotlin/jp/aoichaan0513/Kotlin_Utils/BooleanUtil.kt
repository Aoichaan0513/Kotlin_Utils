package jp.aoichaan0513.Kotlin_Utils

fun <T> Boolean.getValue(v1: T, v2: T): T {
    return if (this) v1 else v2
}