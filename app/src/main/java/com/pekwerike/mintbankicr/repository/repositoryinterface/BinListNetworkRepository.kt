package com.pekwerike.mintbankicr.repository.repositoryinterface

import com.pekwerike.mintbankicr.model.CardDTO

interface BinListNetworkRepository {
    suspend fun getCardDetails(cardNumber: Long): CardDTO
}