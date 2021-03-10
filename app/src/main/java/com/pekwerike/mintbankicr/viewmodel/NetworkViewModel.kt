package com.pekwerike.mintbankicr.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pekwerike.mintbankicr.model.NetworkResult
import com.pekwerike.mintbankicr.repository.repositoryinterface.BinListNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
**/

@HiltViewModel
class NetworkViewModel @Inject constructor(private val binListNetworkRepository: BinListNetworkRepository) :
    ViewModel() {
    private var _cardScanResult = MutableLiveData<CardScanState>()
    val cardScanResult: LiveData<CardScanState> = _cardScanResult

    private var _networkResult = MutableLiveData<NetworkResult>(NetworkResult.NoRequest)
    val networkResult: LiveData<NetworkResult> = _networkResult

    fun cardNumberCollected(cardNumber: Long) {
        if (cardNumber != -1L) {
            _cardScanResult.value = CardScanState.ScanSuccessful(extractedCardNumber = cardNumber)
            getCardDetails(cardNumber)
        }else {
            _cardScanResult.value = CardScanState.ScanUnsuccessful
        }
    }

    fun cardScanningStarted(scanning : CardScanState){
        _cardScanResult.value = scanning
    }

    private fun getCardDetails(cardNumber: Long) {
        _networkResult.value = NetworkResult.Loading
        viewModelScope.launch {
            val result: NetworkResult = async(Dispatchers.IO) {
                binListNetworkRepository.getCardDetails(cardNumber)
            }.await()
            _networkResult.value = result
            _cardScanResult.value = CardScanState.NoScan
        }
    }
}

sealed class CardScanState{
    data class ScanSuccessful(val extractedCardNumber: Long) : CardScanState()
    object ScanningInProgress : CardScanState()
    object ScanUnsuccessful : CardScanState()
    object NoScan : CardScanState()
}