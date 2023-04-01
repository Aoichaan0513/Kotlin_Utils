package jp.aoichaan0513.kotlin_utils

import java.awt.Color

val Color.hex
    get() = "#%02x%02x%02x".format(red, green, blue)