package com.pekwerike.mintbankicr.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pekwerike.mintbankicr.model.CardDTO
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

    private var _cardDetails = MutableLiveData<CardDTO>()
    val cardDetails : LiveData<CardDTO> = _cardDetails

    fun cardNumberCollected(cardNumber: Long) {
        _cardNumber.value = cardNumber
        getCardDetails(_cardNumber.value!!)
    }

    private fun getCardDetails(cardNumber: Long) {
        viewModelScope.launch {
            val cardDetails: CardDTO = async(Dispatchers.IO) {
                binListNetworkRepository.getCardDetails(cardNumber)
            }.await()
            _cardDetails.value = cardDetails
        }
    }
}