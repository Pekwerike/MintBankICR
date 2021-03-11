package com.pekwerike.mintbankicr.ui.screens


import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.pekwerike.mintbankicr.model.CardScanState
import com.pekwerike.mintbankicr.model.NetworkResult
import com.pekwerike.mintbankicr.ui.screens.homescreencomponents.*
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel
import com.pekwerike.mintbankicr.viewmodel.NetworkViewModel

@ExperimentalAnimationApi
@Composable
fun HomePageScreen(
    networkViewModel: NetworkViewModel,
    mainActivityViewModel: MainActivityViewModel
) {

    val shouldShowCameraPreview by
    mainActivityViewModel.shouldShowCameraPreview.observeAsState(false)
    val cardScanState by networkViewModel.cardScanResult.observeAsState(CardScanState.NoScan)
    val networkRequestState by networkViewModel.networkResult.observeAsState(NetworkResult.NoRequest)
    Column(modifier = Modifier.fillMaxSize(1f)) {
        HomePageAppBar(modifier = Modifier.fillMaxWidth())
        AnimatedVisibility(visible = shouldShowCameraPreview) {
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

                CameraPreviewOverlay(
                    modifier = Modifier.fillMaxSize(1f),
                    scanResult = cardScanState,
                    imageScanningInitiated = networkViewModel::cardScanningStarted,
                    cardNumberExtracted = networkViewModel::cardNumberCollected,
                    mainActivityViewModel = mainActivityViewModel
                )

                CameraPreviewBrokenSquareBorder(
                    modifier = Modifier.fillMaxSize(1f)
                )
            }
        }
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize()
        ) {
            CardScannerHelperText(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            NetworkResultMessage(networkRequestState = networkRequestState)
        }
    }
}







