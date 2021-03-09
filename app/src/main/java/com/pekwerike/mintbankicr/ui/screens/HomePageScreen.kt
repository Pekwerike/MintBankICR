package com.pekwerike.mintbankicr.ui.screens

import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.pekwerike.mintbankicr.MainActivityViewModel
import com.pekwerike.mintbankicr.databinding.CameraPreviewLayoutBinding

@Composable
fun HomePageScreen(cameraLifecycleOwner : LifecycleOwner, mainActivityViewModel: MainActivityViewModel) {
    val context = LocalContext.current
    val shouldShowCameraPreview = mainActivityViewModel.shouldShowCameraPreview.observeAsState()
    Column(modifier = Modifier.fillMaxSize()) {

        AndroidViewBinding(CameraPreviewLayoutBinding::inflate) {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener(Runnable {
                val cameraProvider: ProcessCameraProvider =
                    cameraProviderFuture.get()
                val cameraPreview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(this.cameraPreview.createSurfaceProvider())
                    }
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        cameraLifecycleOwner,
                        cameraSelector,
                        cameraPreview
                    )
                } catch (exceptiion: Exception) {

                }
            }, ContextCompat.getMainExecutor(context))
        }
    }
}