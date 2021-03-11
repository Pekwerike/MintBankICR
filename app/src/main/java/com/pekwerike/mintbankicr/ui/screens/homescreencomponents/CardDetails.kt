package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pekwerike.mintbankicr.model.CardDTO
import java.util.*

@ExperimentalAnimationApi
@Composable
fun CardMetaData(cardDTO: CardDTO, modifier: Modifier) {
    AnimatedVisibility(
        visible = true, initiallyVisible = false, enter = fadeIn(
            initialAlpha = 0f, animationSpec = tween(1500, easing = LinearEasing)
        )
    ) {
        Column(modifier = modifier) {
            Text(
                text = "Card Details", style = MaterialTheme.typography.h6,
                fontWeight = SemiBold,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Divider()
            CardMetaDataSingleTextRow(
                label = "Brand", value = (cardDTO.scheme ?: "Unknown Brand").toUpperCase(
                    Locale.ROOT
                ),
                paddingTop = 5
            )
            CardMetaDataSingleTextRow(
                label = "Type", value = cardDTO.type ?: "Unknown Type",
                paddingTop = 5
            )
            CardMetaDataSingleTextRow(
                label = "Bank",
                value = cardDTO.bank?.bankName ?: "Unknown Bank",
                paddingTop = 5
            )
            CardMetaDataSingleTextRow(
                label = "Website",
                value = cardDTO.bank?.bankWebsite ?: "site undefined",
                paddingTop = 5
            )
            CardMetaDataSingleTextRow(
                label = "Country",
                value = cardDTO.country?.name + cardDTO.country?.emoji,
                paddingTop = 5
            )
            CardMetaDataSingleTextRow(
                label = "Currency",
                value = cardDTO.country?.currency ?: "Undefined",
                paddingTop = 5
            )
        }
    }
}

@Composable
fun CardMetaDataSingleTextRow(label: String, value: String, paddingTop: Int) {
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = Bold)) {
            append("$label: ")
        }
        append(value)
    }, style = MaterialTheme.typography.body2, modifier = Modifier.padding(top = paddingTop.dp))
}

@ExperimentalAnimationApi
@Composable
fun ErrorFetchingCardDetails(
    modifier: Modifier, errorMessage: String, errorIcon: ImageVector,
    errorIconTint: Color
) {
    AnimatedVisibility(
        visible = true, initiallyVisible = false, enter = fadeIn(
            initialAlpha = 0f, animationSpec = tween(1500, easing = LinearEasing)
        )
    ) {
        Column(modifier = modifier) {
            Text(
                text = "Card Details", style = MaterialTheme.typography.h6,
                fontWeight = SemiBold,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Divider()
            Column(
                modifier = Modifier.fillMaxSize(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = errorIcon, contentDescription = "",
                    modifier = Modifier.padding(16.dp) .size(150.dp),
                    tint = errorIconTint
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = errorMessage, textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.caption,
                        fontWeight = SemiBold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}