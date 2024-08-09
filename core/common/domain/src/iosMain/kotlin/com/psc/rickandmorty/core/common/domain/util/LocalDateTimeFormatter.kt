package com.psc.rickandmorty.core.common.domain.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSDateFormatterShortStyle
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual object LocalDateFormatter {

    actual fun getShortDateFromLocalDate(localDate: LocalDate): String {
        val timezone = TimeZone.currentSystemDefault()
        val date = localDate.atStartOfDayIn(timezone).toNSDate()
        val formatter = NSDateFormatter()
        val locale = NSLocale.currentLocale
        formatter.locale = locale
        formatter.dateStyle = NSDateFormatterShortStyle

        return formatter.stringFromDate(date)
    }
}