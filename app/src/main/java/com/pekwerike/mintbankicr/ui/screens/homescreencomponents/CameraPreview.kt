package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
    coroutineScope: CoroutineScope,
    modifier: Modifier
) {
    AndroidViewBinding(
        CameraPreviewLayoutBinding::inflate,
        modifier = modifier
            .clickable {
                // take photo
                takePhoto(context, mainActivityViewModel, coroutineScope, networkViewModel)
            }

    ) {
        configureCameraLifecycleAndPreview(context, mainActivityViewModel)
    }
}