package com.pekwerike.mintbankicr.model

import com.squareup.moshi.Json

data class CardDTO(
    val number: CardNumber?,
    val scheme: String?,
    val brand: String?,
    val type: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?
)

data class CardNumber(
    val length: Int?,
    val luhn: Boolean?
)

data class Bank(
    @Json(name = "name") val bankName: String?,
    @Json(name = "url") val bankWebsite: String?,
    @Json(name = "phone") val bankPhone: String?,
    val city: String?
)

data class Country(
    val numeric: String?,
    @Json(name = "alpha2") val alpha_two: String?,
    val name : String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Float?,
    val longitude: Float?
)