package com.pekwerike.mintbankicr.repository.repositoryinterface

import com.pekwerike.mintbankicr.model.CardDTO
import com.pekwerike.mintbankicr.model.NetworkResult
import retrofit2.Response

interface BinListNetworkRepository {
    suspend fun getCardDetails(cardNumber: Long): NetworkResult

}