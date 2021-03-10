package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.withStyle

@Composable
fun CardScannerHelperText(modifier: Modifier) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        val helperText = remember{
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append("Pro tip: ")
                }
                append("Place the front of your debit/credit card in front of the scanner and tap the scanner to begin scanning")
            }
        }
        Text(text = helperText,
        modifier = modifier, style = MaterialTheme.typography.body2)
    }
}