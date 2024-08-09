package com.psc.rickandmorty.core.common.domain.util

import kotlinx.datetime.LocalDate

expect object LocalDateFormatter {
    fun getShortDateFromLocalDate(localDate: LocalDate): String
}