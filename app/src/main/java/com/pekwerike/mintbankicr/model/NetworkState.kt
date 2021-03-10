package com.pekwerike.mintbankicr.model

sealed class NetworkState {
    class Success() : NetworkState()
    class Failure() : NetworkState()
    class Loading() : NetworkState()
}