package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.pekwerike.mintbankicr.cameraconfigurations.configureCameraLifecycleAndPreview
import com.pekwerike.mintbankicr.cameraconfigurations.takePhoto
import com.pekwerike.mintbankicr.databinding.CameraPreviewLayoutBinding
import com.pekwerike.mintbankicr.viewmodel.CardScanState
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope


@Composable
fun CameraPreview(
    mainActivityViewModel: MainActivityViewModel,
    modifier: Modifier
) {
    val context = LocalContext.current
    AndroidViewBinding(
        CameraPreviewLayoutBinding::inflate,
        modifier = modifier) {
        configureCameraLifecycleAndPreview(context,  mainActivityViewModel)
    }
}