package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.pekwerike.mintbankicr.model.CardDTO
import java.util.*

@ExperimentalAnimationApi
@Composable
fun CardMetaData(cardDTO: CardDTO, modifier: Modifier) {
    AnimatedVisibility(
        visible = true, initiallyVisible = false, enter = fadeIn(
            initialAlpha = 0f, animationSpec = tween(5000, easing = LinearEasing)
        )
    ) {
        Column(modifier = modifier) {
            Text(
                text = "Card Details", style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold
            )
            Divider()
            CardMetaDataSingleTextRow(
                label = "Brand", value = (cardDTO.brand ?: "Unknown Brand").toUpperCase(
                    Locale.ROOT
                )
            )
            CardMetaDataSingleTextRow(label = "Type", value = cardDTO.type ?: "Unknown Type")
            CardMetaDataSingleTextRow(
                label = "Bank",
                value = cardDTO.bank?.bankName ?: "Unknown Bank"
            )
            CardMetaDataSingleTextRow(
                label = "Website",
                value = cardDTO.bank?.bankWebsite ?: "site undefined"
            )
            Divider()
            CardMetaDataSingleTextRow(
                label = "Country",
                value = cardDTO.country?.name + cardDTO.country?.emoji
            )
            CardMetaDataSingleTextRow(
                label = "Currency",
                value = cardDTO.country?.currency ?: "Undefined"
            )
        }
    }
}

@Composable
fun CardMetaDataSingleTextRow(label: String, value: String) {
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = Bold)) {
            append("$label: ")
        }
        append(value)
    }, style = MaterialTheme.typography.body2)
}

@ExperimentalAnimationApi
@Composable
fun ErrorFetchingCardDetails(modifier: Modifier, errorMessage: String) {
    AnimatedVisibility(
        visible = true, initiallyVisible = false, enter = fadeIn(
            initialAlpha = 0f, animationSpec = tween(5000, easing = LinearEasing)
        )
    ) {
        Column(modifier = modifier) {
            Text(
                text = "Card Details", style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold
            )
            Divider()
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = errorMessage, textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}