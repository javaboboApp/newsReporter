package com.javabobo.notes.utils

import java.text.DateFormat
import java.util.*

object DateUtils {
    /**
     * Return date in specified format.
     * @param milliSeconds Date in milliseconds
     * @param dateFormat Date format
     * @return String representing date in specified format
     */
    fun getDate(milliSeconds: Long): String? {

        val formatter: DateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.UK)



        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(milliSeconds)
        return formatter.format(calendar.getTime())
    }
}