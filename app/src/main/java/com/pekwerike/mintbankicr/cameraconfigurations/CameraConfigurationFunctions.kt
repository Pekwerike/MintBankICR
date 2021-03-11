package com.pekwerike.mintbankicr.cameraconfigurations

import android.content.Context
import android.util.DisplayMetrics
import android.util.Size
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.pekwerike.mintbankicr.databinding.CameraPreviewLayoutBinding
import com.pekwerike.mintbankicr.model.CardScanState
import com.pekwerike.mintbankicr.ocr.CardCharacterExtractor
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.math.roundToInt
import kotlin.random.Random

/* Overview
    This file contains two functions
    1. configureCameraLifecycleAndPreview extension function
    2. takePhoto function

    Functions description
    1. configureCameraLifecycleAndPreview
        The key responsibility of this function is to bind the camera_preview view in camera_preview_layout.xml
        to a lifecycle aware component like a composition or activity

       configureCameraLifecycleAndPreview function parameters
       @param context -> The current ui scope on which the camera preview will be attached
       @param mainActivityViewModel -> A viewModel class that stores an instance of ImageCapture class

    2. takePhoto
 */
fun CameraPreviewLayoutBinding.configureCameraLifecycleAndPreview(
    context: Context,
    mainActivityViewModel: MainActivityViewModel
) {

    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    cameraProviderFuture.addListener({
        val cameraProvider: ProcessCameraProvider =
            cameraProviderFuture.get()
        val cameraPreview = Preview.Builder()
            .build()
            .also {
                it.setSurfaceProvider(this.cameraPreview.createSurfaceProvider())
            }
        mainActivityViewModel.imageCaptureInstanceReady(
            ImageCapture.Builder()
                .setTargetResolution(
                    Size(
                        DisplayMetrics().also {
                            this.cameraPreview.display.getRealMetrics(it)
                        }.widthPixels,
                        (DisplayMetrics().also {
                            this.cameraPreview.display.getRealMetrics(it)
                        }.heightPixels * 0.6f).roundToInt()
                    )
                )
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                .build()
        )
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

fun takePhoto(
    context: Context,
    mainActivityViewModel: MainActivityViewModel,
    coroutineScope: CoroutineScope,
    imageScanningInitiated : (CardScanState) -> Unit,
    cardNumberCollected: (Long) -> Unit
) {
    val baseDirectory = File(context.getExternalFilesDir(null), "Images")
    if (!baseDirectory.exists()) baseDirectory.mkdirs()
    val imageFile = File(baseDirectory, "IMG${Random.nextInt(10, 1000000)}.jpg")

    val outputOptions = ImageCapture.OutputFileOptions.Builder(imageFile)
        .build()
    mainActivityViewModel.imageCapture.value?.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                imageScanningInitiated(CardScanState.ScanningInProgress)
                // start machine learning algorithm
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        CardCharacterExtractor(
                            context,
                            cardNumberCollected
                        ).getGetCardNumber(imageFile.absolutePath)
                    }
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