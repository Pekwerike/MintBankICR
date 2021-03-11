package com.pekwerike.mintbankicr.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pekwerike.mintbankicr.model.Bank
import com.pekwerike.mintbankicr.model.CardDTO
import com.pekwerike.mintbankicr.model.NetworkResult
import com.pekwerike.mintbankicr.ui.screens.homescreencomponents.*
import com.pekwerike.mintbankicr.viewmodel.CardScanState
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel
import com.pekwerike.mintbankicr.viewmodel.NetworkViewModel
import java.util.*

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
        Column(modifier = Modifier.verticalScroll(state = rememberScrollState())
            .fillMaxSize()) {
            CardScannerHelperText(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            when (networkRequestState) {
                NetworkResult.HttpError.HttpError400 -> ErrorFetchingCardDetails(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    errorMessage = "Sorry, Mint Digital Bank doesn't support this card brand"
                )
                NetworkResult.HttpError.HttpError404 -> ErrorFetchingCardDetails(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    errorMessage = "Sorry, Mint Digital Bank doesn't support this card brand"
                )
                is NetworkResult.HttpError.UnknownError -> ErrorFetchingCardDetails(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    errorMessage = "Service unavailable"
                )
                NetworkResult.Loading -> ErrorFetchingCardDetails(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(), errorMessage = "Fetching card details...")
                NetworkResult.NoInternetConnection -> ErrorFetchingCardDetails(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    errorMessage = "No internet connection to fetch card details, connect to the internet " +
                            "and try again"
                )
                NetworkResult.NoRequest -> {

                }
                is NetworkResult.Success -> {
                    CardMetaData(
                        cardDTO = (networkRequestState as NetworkResult.Success).cardDTO,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}






