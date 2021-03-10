package com.pekwerike.mintbankicr.model

sealed class NetworkResult {
    data class Success(val cardDTO: CardDTO) : NetworkResult()
    object Loading : NetworkResult()
    object NoInternetConnection : NetworkResult()

    sealed class HttpError : NetworkResult() {
        data class UnknownError(val errorCode: Int, val errorMessage: String) : HttpError()
    }
}