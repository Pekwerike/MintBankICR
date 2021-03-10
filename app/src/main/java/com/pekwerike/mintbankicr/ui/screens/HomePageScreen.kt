package com.pekwerike.mintbankicr.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pekwerike.mintbankicr.ui.screens.components.CameraPreview
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


@ExperimentalAnimationApi
@Composable
fun CameraPreviewOverlay(modifier: Modifier) {
    AnimatedVisibility(
        visible = false, initiallyVisible = true,
        exit = slideOutVertically(
            animationSpec = tween(10000, 5000, LinearEasing)
        )
    ) {
        Surface(color = Color.DarkGray.copy(alpha = 0.5f), modifier = modifier) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Tap to scan card", textAlign = TextAlign.Center, modifier = Modifier
                        .fillMaxWidth(1f),
                    color = Color.White
                )
            }

        }
    }
}

@Composable
fun CameraPreviewBrokenSquareBorder(modifier: Modifier) {
    val context = LocalContext.current

    Canvas(modifier = modifier.padding(16.dp), onDraw = {
        val size = this.size
        val scaledDensity = context.resources.displayMetrics.scaledDensity
        drawPath(
            path = Path().apply {
                drawLine(
                    color = Color.White,
                    start = Offset(0f, 0f),
                    end = Offset(15f * scaledDensity, 0f),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(0f, 0f),
                    end = Offset(0f, 15f * scaledDensity),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width, 0f),
                    end = Offset(size.width, 15f * scaledDensity),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width, 0f),
                    end = Offset(size.width - (15f * scaledDensity), 0f),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )

                drawLine(
                    color = Color.White,
                    start = Offset(size.width, size.height),
                    end = Offset(size.width, size.height - 15f * scaledDensity),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width, size.height),
                    end = Offset(size.width - (15f * scaledDensity), size.height),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )

                drawLine(
                    color = Color.White,
                    start = Offset(0f, size.height),
                    end = Offset(0f, size.height - 15f * scaledDensity),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(0f, size.height),
                    end = Offset(15f * scaledDensity, size.height),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )
            },
            color = Color.White, style = Stroke(
                width = 6f * scaledDensity,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round,
                pathEffect = PathEffect.cornerPathEffect(10 * scaledDensity)
            )
        )
    })
}
