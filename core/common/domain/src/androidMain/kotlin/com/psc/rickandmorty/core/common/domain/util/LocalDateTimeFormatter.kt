package com.psc.rickandmorty.core.common.domain.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import java.text.DateFormat

actual object LocalDateFormatter {

    actual fun getShortDateFromLocalDate(localDate: LocalDate): String {
        val timezone = TimeZone.currentSystemDefault()
        val time = localDate.atStartOfDayIn(timezone).toEpochMilliseconds()

        val formatter = DateFormat.getDateInstance(DateFormat.SHORT)
        return formatter.format(time)
    }
}