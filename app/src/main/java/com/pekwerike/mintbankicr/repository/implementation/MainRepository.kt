package com.pekwerike.mintbankicr.repository.implementation

import com.pekwerike.mintbankicr.model.CardDTO
import com.pekwerike.mintbankicr.model.NetworkResult
import com.pekwerike.mintbankicr.network.BinListApi
import com.pekwerike.mintbankicr.repository.repositoryinterface.BinListNetworkRepository
import retrofit2.Response

import javax.inject.Inject

class MainRepository @Inject constructor(private val
binListApi: BinListApi) : BinListNetworkRepository {

    override suspend fun getCardDetails(cardNumber: Long): NetworkResult {
      return  try{
            binListApi.binListApiService().getCardDetails(cardNumber = cardNumber)
                .convertToNetworkResult()
        }catch (exception : Exception){
            NetworkResult.NoInternetConnection
        }
    }

    private fun Response<CardDTO>.convertToNetworkResult(): NetworkResult {
        if (isSuccessful) {
            return NetworkResult.Success(body()!!)
        }else{
           return when(code()){
                // TODO, Messages for various http error
                404 -> NetworkResult.HttpError.HttpError404
                400 -> NetworkResult.HttpError.HttpError400
                else -> NetworkResult.HttpError.UnknownError(code(), message())
           }
        }
    }
}

