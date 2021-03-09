package com.pekwerike.mintbankicr.ui.screens

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.pekwerike.mintbankicr.MainActivity
import com.pekwerike.mintbankicr.databinding.CameraPreviewLayoutBinding
import com.pekwerike.mintbankicr.ocr.CardReaderOCR
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel
import java.util.concurrent.Executors

@ExperimentalAnimationApi
@Composable
fun HomePageScreen(
    cameraLifecycleOwner: LifecycleOwner,
    mainActivityViewModel: MainActivityViewModel
) {
    val context = LocalContext.current
    val shouldShowCameraPreview =
        mainActivityViewModel.shouldShowCameraPreview.observeAsState(false)
    Column(modifier = Modifier.fillMaxSize(1f)) {

        AnimatedVisibility(visible = shouldShowCameraPreview.value) {

            AndroidViewBinding(
                CameraPreviewLayoutBinding::inflate,
                modifier = Modifier.fillMaxSize(1f)
            ) {
                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                cameraProviderFuture.addListener(Runnable {
                    val cameraProvider: ProcessCameraProvider =
                        cameraProviderFuture.get()
                    val cameraPreview = Preview.Builder()
                        .build()
                        .also {
                            it.setSurfaceProvider(this.cameraPreview.createSurfaceProvider())
                        }
                    val imageAnalyzer = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .also {
                            it.setAnalyzer(
                                Executors.newSingleThreadExecutor(),
                                CardReaderOCR(context)
                            )
                        }
                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            cameraLifecycleOwner,
                            cameraSelector,
                            cameraPreview,
                            imageAnalyzer
                        )
                    } catch (exceptiion: Exception) {

                    }
                }, ContextCompat.getMainExecutor(context))
            }
        }
    }
}