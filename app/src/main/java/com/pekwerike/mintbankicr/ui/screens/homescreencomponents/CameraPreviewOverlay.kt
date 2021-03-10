package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideOutVertically
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
    Surface(color = Color.DarkGray.copy(alpha = 0.5f), modifier = modifier.clickable {
        // take photo
        takePhoto(
            context, mainActivityViewModel, coroutineScope,
            imageScanningInitiated, cardNumberExtracted
        )
    }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (scanResult) {
                CardScanState.NoScan -> {
                    CameraPreviewOverlayMessage(modifier)
                }
                is CardScanState.ScanSuccessful -> {
                    AnimatedVisibility(
                        visible = true, initiallyVisible = false,
                        enter = fadeIn(
                            initialAlpha = 0f, animationSpec = spring(
                                dampingRatio = Spring.DampingRatioHighBouncy,
                                stiffness = Spring.StiffnessHigh
                            )
                        )
                    ) {
                        Text(
                            text = scanResult.extractedCardNumber.toString(),
                            textAlign = TextAlign.Center, modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(1f),
                            color = Color.White
                        )
                    }
                }
                CardScanState.ScanUnsuccessful -> {
                    AnimatedVisibility(
                        visible = true, initiallyVisible = false,
                        enter = fadeIn(
                            initialAlpha = 0f, animationSpec = spring(
                                dampingRatio = Spring.DampingRatioHighBouncy,
                                stiffness = Spring.StiffnessHigh
                            )
                        )
                    ) {
                        Text(
                            text = "Scan failed, make sure the scanner captures the card number clearly" +
                                    "or input the number manually",
                            textAlign = TextAlign.Center, modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(1f),
                            color = Color.White
                        )
                    }
                }
                CardScanState.ScanningInProgress -> {
                    AnimatedVisibility(
                        visible = true, initiallyVisible = false,
                        enter = fadeIn(
                            initialAlpha = 0f, animationSpec = spring(
                                dampingRatio = Spring.DampingRatioHighBouncy,
                                stiffness = Spring.StiffnessHigh
                            )
                        )
                    ) {
                        Text(
                            text = "Scanning in progress",
                            textAlign = TextAlign.Center, modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(1f),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun CameraPreviewOverlayMessage(modifier: Modifier) {
    AnimatedVisibility(
        visible = false, initiallyVisible = true,
        exit = slideOutVertically(
            animationSpec = tween(5000, 5000, LinearEasing)
        )
    ) {
        Text(
            text = "Tap to scan card", textAlign = TextAlign.Center, modifier = Modifier
                .fillMaxWidth(1f),
            color = Color.White
        )
    }

}
