package com.pekwerike.mintbankicr.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pekwerike.mintbankicr.ui.screens.homescreencomponents.CameraPreview
import com.pekwerike.mintbankicr.ui.screens.homescreencomponents.CameraPreviewBrokenSquareBorder
import com.pekwerike.mintbankicr.ui.screens.homescreencomponents.CameraPreviewOverlay
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel
import com.pekwerike.mintbankicr.viewmodel.NetworkViewModel

@ExperimentalAnimationApi
@Composable
fun HomePageScreen(
    networkViewModel: NetworkViewModel,
    mainActivityViewModel: MainActivityViewModel
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val shouldShowCameraPreview =
        mainActivityViewModel.shouldShowCameraPreview.observeAsState(false)
    val cardNumber = networkViewModel.cardNumber.observeAsState(5)
    Column(modifier = Modifier.fillMaxSize(1f)) {
        AnimatedVisibility(visible = shouldShowCameraPreview.value) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
                    .height(350.dp)
            ) {
                CameraPreview(
                    context = context,
                    mainActivityViewModel = mainActivityViewModel,
                    networkViewModel = networkViewModel,
                    coroutineScope = coroutineScope,
                    modifier = Modifier.height(350.dp)
                )
                CameraPreviewOverlay(modifier = Modifier.fillMaxSize(1f))
                CameraPreviewBrokenSquareBorder(
                    modifier = Modifier.fillMaxSize(1f)
                )
            }
        }
        Text(text = cardNumber.value.toString(), style = MaterialTheme.typography.h6)
    }
}


