package com.pekwerike.mintbankicr.viewmodel

import androidx.camera.core.ImageCapture
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(){
    private var _shouldShowCameraPreview = MutableLiveData(false)
    val shouldShowCameraPreview : LiveData<Boolean> = _shouldShowCameraPreview

    private var _imageCapture = MutableLiveData<ImageCapture>()
    val imageCapture : LiveData<ImageCapture> = _imageCapture

    fun imageCaptureInstanceReady(imageCapture: ImageCapture){
        _imageCapture.value = imageCapture
    }

    fun cameraPermissionGranted(state : Boolean){
        _shouldShowCameraPreview.value = state
    }
}