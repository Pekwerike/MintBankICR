package com.pekwerike.mintbankicr.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pekwerike.mintbankicr.model.CardDTO
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
    private var _cardNumber = MutableLiveData<Long>()
    val cardNumber: LiveData<Long> = _cardNumber

    private var _networkResult = MutableLiveData<NetworkResult>()
    val networkResult: LiveData<NetworkResult> = _networkResult

    fun cardNumberCollected(cardNumber: Long) {
        if (cardNumber >= 16) {
            _cardNumber.value = cardNumber
            getCardDetails(_cardNumber.value!!)
        }
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