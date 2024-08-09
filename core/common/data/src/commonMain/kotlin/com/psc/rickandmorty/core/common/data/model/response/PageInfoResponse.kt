package com.psc.rickandmorty.core.common.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageInfoResponse(
    @SerialName("count")
    val count: Int,

    @SerialName("pages")
    val pages: Int
)