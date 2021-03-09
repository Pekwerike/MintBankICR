package com.pekwerike.mintbankicr.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(){
    private var _shouldShowCameraPreview = MutableLiveData(false)
    val shouldShowCameraPreview : LiveData<Boolean> = _shouldShowCameraPreview

    fun cameraPermissionGranted(state : Boolean){
        _shouldShowCameraPreview.value = state
    }
}