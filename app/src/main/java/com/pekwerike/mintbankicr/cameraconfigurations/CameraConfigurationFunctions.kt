package com.pekwerike.mintbankicr.cameraconfigurations

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.pekwerike.mintbankicr.databinding.CameraPreviewLayoutBinding
import com.pekwerike.mintbankicr.ocr.CardNumberExtractor
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel
import com.pekwerike.mintbankicr.viewmodel.NetworkViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File


fun CameraPreviewLayoutBinding.configureCameraLifecycleAndPreview(
    context: Context,
    mainActivityViewModel: MainActivityViewModel
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
    networkViewModel: NetworkViewModel
) {
    val baseDirectory = File(context.getExternalFilesDir(null), "Images")
    if (!baseDirectory.exists()) baseDirectory.mkdirs()
    val imageFile = File(baseDirectory, "IMG${System.currentTimeMillis()}.jpg")
    val outputOptions = ImageCapture.OutputFileOptions.Builder(imageFile)
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
                    withContext(Dispatchers.IO) {
                        CardNumberExtractor(
                            context,
                            networkViewModel
                        ).getGetCardNumber(Uri.fromFile(imageFile))
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