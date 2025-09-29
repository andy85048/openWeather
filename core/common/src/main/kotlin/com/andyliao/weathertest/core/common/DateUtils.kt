package com.andyliao.weathertest.core.common

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val dateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val fullDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    fun formatDate(timestamp: Long): String {
        return dateFormat.format(Date(timestamp * 1000))
    }

    fun formatTime(timestamp: Long): String {
        return timeFormat.format(Date(timestamp * 1000))
    }

    fun formatFullDate(timestamp: Long): String {
        return fullDateFormat.format(Date(timestamp))
    }

    fun getCurrentTime(): Long {
        return System.currentTimeMillis()
    }

    fun isExpired(timestamp: Long, expiryDuration: Long = Constants.CACHE_EXPIRY_TIME): Boolean {
        return getCurrentTime() - timestamp > expiryDuration
    }
}