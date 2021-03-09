package com.pekwerike.mintbankicr.ui.screens

import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.pekwerike.mintbankicr.databinding.CameraPreviewLayoutBinding

@Composable
fun HomePageScreen(cameraLifecycleOwner : LifecycleOwner) {
    val context = LocalContext.current
    AndroidViewBinding(CameraPreviewLayoutBinding::inflate) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider : ProcessCameraProvider =
                cameraProviderFuture.get()
            val cameraPreview = Preview.Builder()
                .build()
                .also{
                    it.setSurfaceProvider(this.cameraPreview.createSurfaceProvider())
                }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try{
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(cameraLifecycleOwner, cameraSelector, cameraPreview)
            }catch (exceptiion : Exception){

            }
        }, ContextCompat.getMainExecutor(context))
    }
}