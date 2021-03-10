package com.pekwerike.mintbankicr.repository.implementation

import com.pekwerike.mintbankicr.dto.CardDTO
import com.pekwerike.mintbankicr.networkservice.BinListApi
import com.pekwerike.mintbankicr.networkservice.BinListApiService
import com.pekwerike.mintbankicr.repository.repositoryinterface.BinListNetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository() : BinListNetworkRepository {

    override suspend fun getCardDetails(cardNumber: Long): CardDTO {
       return withContext(Dispatchers.IO) {
            BinListApi.binListApiService.getCardDetails(cardNumber = cardNumber)
        }
    }
}

