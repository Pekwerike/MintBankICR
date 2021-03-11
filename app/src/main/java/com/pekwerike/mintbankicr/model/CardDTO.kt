package com.pekwerike.mintbankicr.model



data class CardDTO(
    val number: CardNumber?,
    val scheme: String?,
    val brand: String?,
    val type: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?
)




