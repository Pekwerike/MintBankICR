package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.CreditCardOff
import androidx.compose.material.icons.rounded.SignalCellularConnectedNoInternet0Bar
import androidx.compose.material.icons.rounded.WifiTetheringErrorRounded
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pekwerike.mintbankicr.model.NetworkResult

@ExperimentalAnimationApi
@Composable
fun NetworkResultMessage(networkRequestState: NetworkResult) {
    when (networkRequestState) {
        NetworkResult.HttpError.HttpError400 -> ErrorFetchingCardDetails(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            errorMessage = "Sorry, Mint Digital Bank doesn't support this card brand",
            errorIcon = Icons.Rounded.CreditCardOff,
            errorIconTint = Color.Red.copy(0.5f)
        )
        NetworkResult.HttpError.HttpError404 -> ErrorFetchingCardDetails(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            errorMessage = "Sorry, Mint Digital Bank doesn't support this card brand",
            errorIcon = Icons.Rounded.CreditCardOff,
            errorIconTint = Color.Red.copy(0.5f)
        )
        is NetworkResult.HttpError.UnknownError -> ErrorFetchingCardDetails(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            errorMessage = "Service unavailable",
            errorIcon = Icons.Rounded.WifiTetheringErrorRounded,
            errorIconTint = Color.Blue.copy(0.5f)
        )
        NetworkResult.Loading -> ErrorFetchingCardDetails(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(), errorMessage = "Fetching card details...",
            errorIcon = Icons.Rounded.CreditCard,
            errorIconTint = Color.Blue.copy(0.5f)
        )

        NetworkResult.NoInternetConnection -> ErrorFetchingCardDetails(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            errorMessage = "No internet connection to fetch card details, connect to the internet " +
                    "and try again",
            errorIcon = Icons.Rounded.SignalCellularConnectedNoInternet0Bar,
            errorIconTint = Color.Blue.copy(0.5f)
        )
        NetworkResult.NoRequest -> {

        }
        is NetworkResult.Success -> {
            CardMetaData(
                cardDTO = (networkRequestState as NetworkResult.Success).cardDTO,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            )
        }
    }
}