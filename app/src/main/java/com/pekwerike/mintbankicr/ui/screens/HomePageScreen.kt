package com.pekwerike.mintbankicr.ui.screens

import android.content.Context
import android.widget.Toast
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

                if (networkRequestState == NetworkResult.NoRequest ||
                    networkRequestState ==  NetworkResult.Loading) {
                    CameraPreviewOverlay(
                        modifier = Modifier.fillMaxSize(1f),
                        scanResult = cardScanState,
                        imageScanningInitiated = networkViewModel::cardScanningStarted,
                        cardNumberExtracted = networkViewModel::cardNumberCollected,
                        mainActivityViewModel = mainActivityViewModel
                    )
                } else if(cardScanState == CardScanState.NoScan){
                    CameraPreviewOverlayNetworkState(networkResult = networkRequestState)
                    CameraPreviewOverlay(
                        modifier = Modifier.fillMaxSize(1f),
                        scanResult = cardScanState,
                        imageScanningInitiated = networkViewModel::cardScanningStarted,
                        cardNumberExtracted = networkViewModel::cardNumberCollected,
                        mainActivityViewModel = mainActivityViewModel
                    )
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
   val localContext = LocalContext.current
    when (networkResult) {
        is NetworkResult.Success -> {
            Toast.makeText(localContext, "Result fetched", Toast.LENGTH_SHORT).show()
           CardDetails(networkResult = networkResult)
        }
        NetworkResult.HttpError.HttpError400 -> {

        }
        NetworkResult.HttpError.HttpError404 -> {
            Toast.makeText(localContext, "We don't support verve card", Toast.LENGTH_SHORT).show()
        }
        is NetworkResult.HttpError.UnknownError -> {
            Toast.makeText(localContext, networkResult.errorCode, Toast.LENGTH_SHORT).show()
        }
        NetworkResult.Loading -> {

        }
        NetworkResult.NoInternetConnection -> {
            Toast.makeText(localContext, "Alaye check your internet", Toast.LENGTH_SHORT).show()
        }
        NetworkResult.NoRequest -> {

        }
    }
}




