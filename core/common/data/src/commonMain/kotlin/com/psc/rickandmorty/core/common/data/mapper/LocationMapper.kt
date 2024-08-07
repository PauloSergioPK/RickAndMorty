package com.psc.rickandmorty.core.common.data.mapper

import com.psc.rickandmorty.core.common.data.model.LocationResponse
import com.psc.rickandmorty.core.common.domain.model.Location

fun LocationResponse.toLocation(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}