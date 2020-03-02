package ru.skillbranch.devintensive.extensions

import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi

fun String.truncate(count: Int = 16): String {
    return "${this.substring(0, count).trimEnd()}..."
}

fun String.stripHtml(): String {
    return ""
}