package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.pekwerike.mintbankicr.cameraconfigurations.configureCameraLifecycleAndPreview
import com.pekwerike.mintbankicr.databinding.CameraPreviewLayoutBinding
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel

@Composable
fun CameraPreview(
    mainActivityViewModel: MainActivityViewModel,
    modifier: Modifier
) {
    val context = LocalContext.current
    AndroidViewBinding(
        CameraPreviewLayoutBinding::inflate,
        modifier = modifier
    ) {
        configureCameraLifecycleAndPreview(context, mainActivityViewModel)
    }
}