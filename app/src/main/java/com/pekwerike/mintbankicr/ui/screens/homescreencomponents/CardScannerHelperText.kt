package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun CardScannerHelperText(modifier: Modifier) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        val helperText = remember{
            buildAnnotatedString {
                withStyle(style = ParagraphStyle(textIndent = TextIndent(0.sp,
                15.sp))){
                    withStyle(style = SpanStyle(fontWeight = Bold, fontSize = 16.sp)){
                        append("Pro tip: \n")
                    }
                    append("\n")
                    append("1. Place the front of your card in front of the scanner and tap the scanner to begin scanning.\n")
                    append("2. Ensure that your internet connection is on and stable.\n")
                }
            }
        }
        Text(text = helperText,
        modifier = modifier, style = MaterialTheme.typography.body2)
    }
}