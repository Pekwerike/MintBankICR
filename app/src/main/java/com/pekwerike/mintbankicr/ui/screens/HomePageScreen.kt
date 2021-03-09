package com.pekwerike.mintbankicr.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.pekwerike.mintbankicr.databinding.CameraPreviewLayoutBinding
import com.pekwerike.mintbankicr.ocr.CardNumberExtractor
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.Executors

@ExperimentalAnimationApi
@Composable
fun HomePageScreen(
    cameraLifecycleOwner: LifecycleOwner,
    mainActivityViewModel: MainActivityViewModel
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val shouldShowCameraPreview =
        mainActivityViewModel.shouldShowCameraPreview.observeAsState(false)
    Column(modifier = Modifier.fillMaxSize(1f)) {

        AnimatedVisibility(visible = shouldShowCameraPreview.value) {

            AndroidViewBinding(
                CameraPreviewLayoutBinding::inflate,
                modifier = Modifier
                    .fillMaxSize(1f)
                    .clickable {
                        // take photo
                        val baseDirectory = File(context.getExternalFilesDir(null), "Images")
                        if (!baseDirectory.exists()) baseDirectory.mkdirs()
                        val imageFile = File(baseDirectory, "IMG${System.currentTimeMillis()}.jpg")
                        val outputOptions = ImageCapture.OutputFileOptions
                            .Builder(imageFile)
                            .build()
                        mainActivityViewModel.imageCapture.value?.takePicture(
                            outputOptions,
                            ContextCompat.getMainExecutor(context),
                            object : ImageCapture.OnImageSavedCallback {
                                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                    Toast
                                        .makeText(context, "Image captured", Toast.LENGTH_SHORT)
                                        .show()
                                    // start machine learning algorithm
                                        coroutineScope.launch {
                                            val cardNumber = async(Dispatchers.IO) {
                                                CardNumberExtractor(context).getGetCardNumber(Uri.fromFile(imageFile))
                                            }.await()
                                            // pass card number to the viewmodel to make the network call
                                        }

                                }

                                override fun onError(exception: ImageCaptureException) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Image capture failed",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            }
                        )

                    }
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
                    mainActivityViewModel.imageCaptureInstanceReady(
                        ImageCapture.Builder()
                            .build()
                    )

                    val imageAnalyzer = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .also {
                            it.setAnalyzer(
                                Executors.newSingleThreadExecutor(),
                                CardReaderOCR(context)
                            )
                        }
                    val cameraSelector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()
                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            context as LifecycleOwner,
                            cameraSelector,
                            cameraPreview,
                            mainActivityViewModel.imageCapture.value
                        )
                    } catch (exception: Exception) {

                    }
                }, ContextCompat.getMainExecutor(context))
            }
        }
    }
}