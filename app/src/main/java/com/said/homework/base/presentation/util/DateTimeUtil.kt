package com.said.homework.base.presentation.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ahmed Sa'eed on 25.11.2020.
 */
object DateTimeUtil {
    const val SERVER_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'+0000"
    const val SERVER_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm"
    fun convertToDateString(date: Long, dateFormat: String?): String {
        var dateStr = ""
        try {
            @SuppressLint("SimpleDateFormat") val df = SimpleDateFormat(dateFormat)
            dateStr = df.format(Date(date))
        } catch (ignored: Exception) {
        }
        return dateStr
    }

    fun convertToDateString(date: Long, dateFormat: String?, locale: Locale?): String {
        var dateStr = ""
        try {
            @SuppressLint("SimpleDateFormat") val df = SimpleDateFormat(dateFormat, locale)
            dateStr = df.format(Date(date))
        } catch (ignored: Exception) {
        }
        return dateStr
    }

    @SuppressLint("SimpleDateFormat")
    fun convertToDate(date: String?, format: String?): Date? {
        val df: DateFormat = SimpleDateFormat(format)
        var startDate: Date? = null
        try {
            startDate = df.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return startDate
    }
}