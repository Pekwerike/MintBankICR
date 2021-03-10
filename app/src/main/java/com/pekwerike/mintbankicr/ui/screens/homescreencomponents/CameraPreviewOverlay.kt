package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pekwerike.mintbankicr.cameraconfigurations.takePhoto
import com.pekwerike.mintbankicr.viewmodel.CardScanState
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalAnimationApi
@Composable
fun CameraPreviewOverlay(
    modifier: Modifier,
    scanResult: CardScanState,
    imageScanningInitiated: (CardScanState) -> Unit,
    cardNumberExtracted: (Long) -> Unit,
    mainActivityViewModel: MainActivityViewModel

) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable {
            if (scanResult == CardScanState.NoScan) {
                takePhoto(
                    context, mainActivityViewModel, coroutineScope,
                    imageScanningInitiated, cardNumberExtracted
                )
            }
        }
    ) {
        when (scanResult) {
            CardScanState.NoScan -> {
                ScanStateMessage(
                    modifier = modifier,
                    message = "Tap to scan card",
                    visibilityDuration = 3000
                )
            }
            is CardScanState.ScanSuccessful -> {
                    ScanStateMessage(
                        modifier = modifier,
                        message = scanResult.extractedCardNumber.toString(),
                        visibilityDuration = 10000
                    )
            }
            CardScanState.ScanUnsuccessful -> {
                ScanStateMessage(
                    modifier = modifier,
                    message = "Scan failed, make sure the scanner captures the card number clearly" +
                            " or input the number manually",
                    visibilityDuration = 10000
                )
                coroutineScope.launch(Dispatchers.Default) {
                    delay(10000)
                    withContext(Dispatchers.Main){
                        imageScanningInitiated(CardScanState.NoScan)
                    }
                }
            }
            CardScanState.ScanningInProgress -> {
                ScanStateMessage(modifier = modifier, message = "Scanning in progress",
                visibilityDuration = 20000)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ScanStateMessage(modifier: Modifier, message: String, visibilityDuration : Int) {
    AnimatedVisibility(
        visible = false, initiallyVisible = true,
        exit = fadeOut(
             animationSpec = tween(durationMillis = 5000,
                 delayMillis = visibilityDuration, easing = LinearEasing )
        )
    ) {
        Surface(
            color = Color.DarkGray.copy(alpha = 0.5f), modifier = modifier
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = message, textAlign = TextAlign.Center, modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(1f),
                    color = Color.White
                )
            }
        }
    }
}

