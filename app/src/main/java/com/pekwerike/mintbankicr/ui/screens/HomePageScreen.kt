package com.pekwerike.mintbankicr.ui.screens

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.pekwerike.mintbankicr.model.NetworkResult
import com.pekwerike.mintbankicr.ui.screens.homescreencomponents.*
import com.pekwerike.mintbankicr.viewmodel.CardScanState
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel
import com.pekwerike.mintbankicr.viewmodel.NetworkViewModel

@ExperimentalAnimationApi
@Composable
fun HomePageScreen(
    networkViewModel: NetworkViewModel,
    mainActivityViewModel: MainActivityViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    val shouldShowCameraPreview =
        mainActivityViewModel.shouldShowCameraPreview.observeAsState(false)
    val cardScanState by networkViewModel.cardScanResult.observeAsState(CardScanState.NoScan)
    val networkRequestState by networkViewModel.networkResult.observeAsState(NetworkResult.NoRequest)
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
                    mainActivityViewModel = mainActivityViewModel,
                    modifier = Modifier.height(350.dp)
                )

                if (networkRequestState == NetworkResult.NoRequest) {
                    CameraPreviewOverlay(
                        modifier = Modifier.fillMaxSize(1f),
                        scanResult = cardScanState,
                        imageScanningInitiated = networkViewModel::cardScanningStarted,
                        cardNumberExtracted = networkViewModel::cardNumberCollected,
                        mainActivityViewModel = mainActivityViewModel
                    )
                } else {
                    CameraPreviewOverlayNetworkState(networkResult = networkRequestState)
                }
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

@Composable
fun CameraPreviewOverlayNetworkState(networkResult: NetworkResult) {

    when (networkResult) {
        is NetworkResult.Success -> {
           // TODO Open Window dialog with user card details
        }
    }
}




