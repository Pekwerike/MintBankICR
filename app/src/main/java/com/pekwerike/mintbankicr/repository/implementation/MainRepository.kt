package com.pekwerike.mintbankicr.repository.implementation

import com.pekwerike.mintbankicr.dto.CardDTO
import com.pekwerike.mintbankicr.networkservice.BinListApi
import com.pekwerike.mintbankicr.networkservice.BinListApiService
import com.pekwerike.mintbankicr.repository.repositoryinterface.BinListNetworkRepository
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.async
import javax.inject.Inject

class MainRepository @Inject constructor() : BinListNetworkRepository {

    override suspend fun getCardDetails(cardNumber: Long): CardDTO {
        return BinListApi.binListApiService.getCardDetails(cardNumber = cardNumber)
    }
}

