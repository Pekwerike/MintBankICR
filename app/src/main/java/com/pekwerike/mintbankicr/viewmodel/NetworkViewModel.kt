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
    private var _cardScanResult = MutableLiveData<CardScanResult>()
    val cardScanResult: LiveData<CardScanResult> = _cardScanResult

    private var _networkResult = MutableLiveData<NetworkResult>()
    val networkResult: LiveData<NetworkResult> = _networkResult

    fun cardNumberCollected(cardNumber: Long) {
        if (cardNumber != -1L) {
            _cardScanResult.value = CardScanResult.ScanSuccessful(extractedCardNumber = cardNumber)
            getCardDetails(cardNumber)
        }else {
            _cardScanResult.value = CardScanResult.ScanUnsuccessful
        }
    }

    fun cardScanningStarted(){
        _cardScanResult.value = CardScanResult.ScanningInProgress
    }

    private fun getCardDetails(cardNumber: Long) {
        _networkResult.value = NetworkResult.Loading
        viewModelScope.launch {
            val result: NetworkResult = async(Dispatchers.IO) {
                binListNetworkRepository.getCardDetails(cardNumber)
            }.await()
            _networkResult.value = result
        }
    }
}

sealed class CardScanResult{
    data class ScanSuccessful(val extractedCardNumber: Long) : CardScanResult()
    object ScanningInProgress : CardScanResult()
    object ScanUnsuccessful : CardScanResult()
    object NoScan : CardScanResult()
}