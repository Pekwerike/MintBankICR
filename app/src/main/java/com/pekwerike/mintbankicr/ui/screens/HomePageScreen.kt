package com.pekwerike.mintbankicr.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.pekwerike.mintbankicr.ui.screens.homescreencomponents.*
import com.pekwerike.mintbankicr.viewmodel.CardScanResult
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
    val cardScanState by networkViewModel.cardScanResult.observeAsState(CardScanResult.NoScan)
    Column(modifier = Modifier.fillMaxSize(1f)) {
        HomePageAppBar(modifier = Modifier.fillMaxWidth())
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
                    coroutineScope = coroutineScope,
                    modifier = Modifier.height(350.dp),
                    imageScanningInitiated = networkViewModel::cardScanningStarted,
                    cardNumberCollected = networkViewModel::cardNumberCollected
                )

                CameraPreviewOverlay(modifier = Modifier.fillMaxSize(1f))
                CameraPreviewBrokenSquareBorder(
                    modifier = Modifier.fillMaxSize(1f)
                )
            }
        }
        CardScannerHelperText(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}


