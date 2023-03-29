package jp.aoichaan0513.Kotlin_Utils

import java.io.PrintWriter
import java.io.StringWriter

val Exception.stacktraceMessage
    get() = StringWriter().use { writer ->
        PrintWriter(writer).use { printStackTrace(it) }
        writer.toString()
    }

val Throwable.stacktraceMessage
    get() = StringWriter().use { writer ->
        PrintWriter(writer).use { printStackTrace(it) }
        writer.toString()
    }