package com.pekwerike.mintbankicr.model

import com.squareup.moshi.Json

data class Bank(
    @Json(name = "name") val bankName: String?,
    @Json(name = "url") val bankWebsite: String?,
    @Json(name = "phone") val bankPhone: String?,
    val city: String?
)