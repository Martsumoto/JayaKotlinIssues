package com.marcelokmats.jayakotlinissues.util

import java.text.SimpleDateFormat
import java.util.*


object DateUtil {

    fun formatDate(date : Date, format : String = DATE_FORMAT) : String {
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        return formatter.format(date)
    }
}