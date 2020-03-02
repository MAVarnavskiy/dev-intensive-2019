package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

public fun Date.humanizeDiff(date: Date = Date()): String {
    val isFuture = (date.time - this.time) < 0
    val firstArg = if(isFuture) this.time else date.time
    val secondArg = if(isFuture) date.time else this.time

    var dateLagSeconds = ((firstArg - secondArg) / SECOND).toInt()
    var dateLagMinutes = (dateLagSeconds / 60).toInt()
    var dateLagHours = (dateLagMinutes / 60).toInt()
    var dateLagDays = (dateLagHours / 24).toInt()

    if (isFuture){
        dateLagSeconds = abs(dateLagSeconds) + 1
    }

    return  when {
        dateLagDays > 360 -> if(isFuture) "более чем через год" else "более года назад"
        dateLagHours > 26 && dateLagDays <= 360 -> "${if(isFuture) "через " else ""}$dateLagDays ${getNumeralForm(dateLagDays, "день", "дня", "дней")} ${if(isFuture) "" else "назад"}"
        dateLagHours > 22 && dateLagDays <= 26 -> "${if(isFuture) "через день" else "день назад"}"
        dateLagMinutes > 75 && dateLagHours <= 22 -> "${if(isFuture) "через " else ""}$dateLagHours ${getNumeralForm(dateLagHours, "час", "часа", "часов")} ${if(isFuture) "" else "назад"}"
        dateLagMinutes > 45 && dateLagMinutes <= 75 -> "${if(isFuture) "через час" else "час назад"}"
        dateLagSeconds > 75 && dateLagMinutes <= 45 -> "${if(isFuture) "через " else ""}$dateLagMinutes ${getNumeralForm(dateLagMinutes, "минуту", "минуты", "минут")} ${if(isFuture) "" else "назад"}"
        dateLagSeconds > 45 && dateLagSeconds <= 75 -> "${if(isFuture) "через минуту" else "минуту назад"}"
        dateLagSeconds > 1 && dateLagSeconds <= 45 -> "${if(isFuture) "через несколько секунд" else "несколько секунд назад"}"
        else -> "только что"
    }

}

fun getNumeralForm(value: Int, form1: String, form2: String, form5: String): String {
    val value100 = abs(value) % 100
    val value10 = value100 % 10
    return when {
        value10 == 0 || value10 in 5..9 || value100 in 11..14 -> form5
        value10 in 2..4 && value100 !in 12..14 -> form2
        value10 == 1 && value100 != 11-> form1
        else -> form5
    }
}

enum class TimeUnits {
    SECOND
    {
        override fun plural(value: Int): String {
            return "$value ${getNumeralForm(value, "секунду", "секунды", "секунд")}"
        }
    },
    MINUTE
    {
        override fun plural(value: Int): String {
            return "$value ${getNumeralForm(value, "минуту", "минуты", "минут")}"
        }
    },
    HOUR
    {
        override fun plural(value: Int): String {
            return "$value ${getNumeralForm(value, "час", "часа", "часов")}"
        }
    },
    DAY
    {
        override fun plural(value: Int): String {
            return "$value ${getNumeralForm(value, "день", "дня", "дней")}"
        }
    };

    abstract fun plural(value: Int): String

}
