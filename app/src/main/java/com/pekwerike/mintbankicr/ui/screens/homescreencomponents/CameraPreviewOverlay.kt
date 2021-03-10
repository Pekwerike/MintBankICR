package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

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