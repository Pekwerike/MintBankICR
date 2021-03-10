package com.pekwerike.mintbankicr.repository.implementation

import com.pekwerike.mintbankicr.dto.CardDTO
import com.pekwerike.mintbankicr.networkservice.BinListApi
import com.pekwerike.mintbankicr.networkservice.BinListApiService
import com.pekwerike.mintbankicr.repository.repositoryinterface.BinListNetworkRepository

class MainRepository() : BinListNetworkRepository {

    override fun getCardDetails(cardNumber: Long): CardDTO {
        return BinListApi.binListApiService.getCardDetails(cardNumber = cardNumber)
    }
}

