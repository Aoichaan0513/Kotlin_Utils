package jp.aoichaan0513.Kotlin_Utils

import java.io.PrintWriter
import java.io.StringWriter

val Exception.message
    get() = StringWriter().use {
        PrintWriter(it).use { printStackTrace(it) }
        it.toString()
    }

val Throwable.message
    get() = StringWriter().use {
        PrintWriter(it).use { printStackTrace(it) }
        it.toString()
    }