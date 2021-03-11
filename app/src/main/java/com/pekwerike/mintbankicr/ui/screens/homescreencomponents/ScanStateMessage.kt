package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun ScanStateMessage(modifier: Modifier, message: String, visibilityDuration: Int) {
    AnimatedVisibility(
        visible = false, initiallyVisible = true,
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = 4000,
                delayMillis = visibilityDuration, easing = LinearEasing
            )
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