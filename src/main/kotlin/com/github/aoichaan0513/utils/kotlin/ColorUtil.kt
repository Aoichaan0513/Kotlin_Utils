package com.github.aoichaan0513.utils.kotlin

import java.awt.Color

val Color.hex
    get() = "#%02x%02x%02x".format(red, green, blue)