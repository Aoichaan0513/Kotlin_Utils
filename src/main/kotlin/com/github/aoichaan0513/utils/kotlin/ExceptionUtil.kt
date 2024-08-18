package com.github.aoichaan0513.utils.kotlin

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