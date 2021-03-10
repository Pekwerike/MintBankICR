package com.pekwerike.mintbankicr.repository.implementation

import com.pekwerike.mintbankicr.model.CardDTO
import com.pekwerike.mintbankicr.model.NetworkResult
import com.pekwerike.mintbankicr.networkservice.BinListApi
import com.pekwerike.mintbankicr.repository.repositoryinterface.BinListNetworkRepository
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.HTTP

import javax.inject.Inject

class MainRepository @Inject constructor() : BinListNetworkRepository {

    override suspend fun getCardDetails(cardNumber: Long): NetworkResult {
      return  try{
            BinListApi.binListApiService.getCardDetails(cardNumber = cardNumber)
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
                // TODO, Messages for various http er
                404 -> NetworkResult.HttpError.HttpError404
                400 -> NetworkResult.HttpError.HttpError400
                else -> NetworkResult.HttpError.UnknownError(code(), message())
           }
        }
    }
}

