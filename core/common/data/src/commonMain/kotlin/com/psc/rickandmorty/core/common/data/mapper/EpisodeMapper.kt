package com.psc.rickandmorty.core.common.data.mapper

import com.psc.rickandmorty.core.common.data.model.EpisodeResponse
import com.psc.rickandmorty.core.common.domain.model.Episode
import kotlinx.datetime.LocalDate

fun EpisodeResponse.toEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        airDate = airDate.toLocalDate(),
        seasonAndEpisode = seasonAndEpisode
    )
}

private fun String.toLocalDate(): LocalDate {
    val slicedDate = this.replace(",", "").split(" ")
    val monthString = slicedDate[0].uppercase()
    val dayString = slicedDate[1]
    val yearString = slicedDate[2]

    val month = kotlinx.datetime.Month.valueOf(monthString).ordinal.inc()
    val day = dayString.toInt()
    val year = yearString.toInt()

    return LocalDate(year = year, monthNumber = month, dayOfMonth = day)
}