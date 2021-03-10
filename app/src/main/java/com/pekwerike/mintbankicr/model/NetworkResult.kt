package com.pekwerike.mintbankicr.model

sealed class NetworkResult {
    data class Success(val cardDTO: CardDTO) : NetworkResult()
    object Loading : NetworkResult()
    object NoInternetConnection : NetworkResult()
    object NoRequest: NetworkResult()

    sealed class HttpError : NetworkResult() {
        object HttpError404 : HttpError()
        object HttpError400 : HttpError()
        data class UnknownError(val errorCode: Int, val errorMessage: String) : HttpError()
    }
}