package com.pekwerike.mintbankicr.repository.repositoryinterface

import com.pekwerike.mintbankicr.dto.CardDTO

interface BinListNetworkRepository {
    suspend fun getCardDetails(cardNumber: Long): CardDTO
}