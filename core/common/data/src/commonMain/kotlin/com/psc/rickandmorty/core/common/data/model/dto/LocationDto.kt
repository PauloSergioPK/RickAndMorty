package com.psc.rickandmorty.core.common.data.model.dto

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

internal class LocationDto: RealmObject {
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var type: String = ""
    var dimension: String = ""
}