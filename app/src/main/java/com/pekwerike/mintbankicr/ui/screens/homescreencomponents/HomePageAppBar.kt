package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun HomePageAppBar(modifier: Modifier) {
    TopAppBar(
        title = {
            Text(
                text = "Mint Digital Bank ICR", textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.fillMaxWidth()
            )
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}