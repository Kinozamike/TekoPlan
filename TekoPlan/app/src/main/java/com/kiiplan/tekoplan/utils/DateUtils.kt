package com.kiiplan.tekoplan.utils

import java.util.*

object DateUtils {

    fun startOfToday(): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }

    fun startOfCurrentMonth(): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, 1)
        return startOfTodayFrom(cal)
    }

    fun startOfCurrentQuarter(): Long {
        val cal = Calendar.getInstance()
        val month = cal.get(Calendar.MONTH)
        cal.set(Calendar.MONTH, month / 3 * 3)
        cal.set(Calendar.DAY_OF_MONTH, 1)
        return startOfTodayFrom(cal)
    }

    fun startOfCurrentYear(): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.MONTH, 0)
        cal.set(Calendar.DAY_OF_MONTH, 1)
        return startOfTodayFrom(cal)
    }

    fun now(): Long = System.currentTimeMillis()

    private fun startOfTodayFrom(cal: Calendar): Long {
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }
}
