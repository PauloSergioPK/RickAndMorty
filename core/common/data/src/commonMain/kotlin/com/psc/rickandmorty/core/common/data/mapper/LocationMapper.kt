package com.psc.rickandmorty.core.common.data.mapper

import com.psc.rickandmorty.core.common.data.model.dto.LocationDto
import com.psc.rickandmorty.core.common.data.model.response.LocationResponse
import com.psc.rickandmorty.core.common.domain.model.Location

fun LocationResponse.toLocation(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}

internal fun Location.toLocationDto(): LocationDto {
    return LocationDto().apply {
        id = this@toLocationDto.id
        name = this@toLocationDto.name
        type = this@toLocationDto.type
        dimension = this@toLocationDto.dimension
    }
}

internal fun LocationDto.toLocation(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}