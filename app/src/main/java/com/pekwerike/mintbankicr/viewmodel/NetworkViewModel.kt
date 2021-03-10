package com.pekwerike.mintbankicr.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NetworkViewModel : ViewModel() {
    private var _cardNumber = MutableLiveData<Long>()
    val cardNumber: LiveData<Long> = _cardNumber

    fun cardNumberCollected(cardNumber: Long) {
        _cardNumber.value = cardNumber
    }

}