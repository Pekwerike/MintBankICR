package com.pekwerike.mintbankicr.repository.repositoryinterface

import com.pekwerike.mintbankicr.dto.CardDTO

interface BinListNetworkRepository {
    fun getCardDetails(cardNumber: Long): CardDTO
}