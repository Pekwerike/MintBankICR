package com.pekwerike.mintbankicr.ui.screens.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.pekwerike.mintbankicr.cameraconfigurations.configureCameraLifecycleAndPreview
import com.pekwerike.mintbankicr.cameraconfigurations.takePhoto
import com.pekwerike.mintbankicr.databinding.CameraPreviewLayoutBinding
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel
import com.pekwerike.mintbankicr.viewmodel.NetworkViewModel
import kotlinx.coroutines.CoroutineScope


@Composable
fun CameraPreview(
    context: Context,
    mainActivityViewModel: MainActivityViewModel,
    networkViewModel: NetworkViewModel,
    coroutineScope: CoroutineScope
) {
    AndroidViewBinding(
        CameraPreviewLayoutBinding::inflate,
        modifier = Modifier
            .fillMaxSize(1f)
            .clickable {
                // take photo
                takePhoto(context, mainActivityViewModel, coroutineScope, networkViewModel)
            }
    ) {
        configureCameraLifecycleAndPreview(context, mainActivityViewModel)
    }
}