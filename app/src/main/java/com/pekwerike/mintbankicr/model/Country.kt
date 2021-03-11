package com.pekwerike.mintbankicr.model

import com.squareup.moshi.Json

data class Country(
    val numeric: String?,
    @Json(name = "alpha2") val alpha_two: String?,
    val name : String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Float?,
    val longitude: Float?
)