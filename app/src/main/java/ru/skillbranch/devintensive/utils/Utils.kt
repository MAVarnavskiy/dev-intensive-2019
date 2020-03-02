package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var result: String = ""
        for (value in payload) {
            var currentValue = value.toString()
            var isUpper = currentValue == currentValue.toUpperCase()
            var translateValue =
                when(currentValue.toLowerCase()){
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и" -> "i"
                "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sh"
                "щ" -> "sh'"
                "ъ" -> ""
                "ы" -> "i"
                "ь" -> ""
                "э" -> "e"
                "ю" -> "yu"
                "я" -> "ya"
                " " -> divider
                else -> currentValue
            }
            result += if(isUpper) translateValue.substring(0, 1).toUpperCase() + translateValue.substring(1, 1) else translateValue
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstInitial = when {
            firstName?.trim() == null || firstName.trim().isEmpty() -> ""
            else -> firstName.trim().substring(0, 1).toUpperCase(Locale.ROOT)
        }

        val lastInitial = when {
            lastName?.trim() == null || lastName.trim().isEmpty() -> ""
            else -> lastName.trim().substring(0, 1).toUpperCase(Locale.ROOT)
        }
        return if("${firstInitial}${lastInitial}".isEmpty()) null else "${firstInitial}${lastInitial}"
    }
}