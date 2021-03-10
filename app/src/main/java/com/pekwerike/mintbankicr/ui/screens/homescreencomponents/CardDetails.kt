package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.pekwerike.mintbankicr.model.NetworkResult
import java.util.*

@Composable
fun CardDetails(networkResult: NetworkResult.Success) {
    Dialog(onDismissRequest = {

    }) {
        Card(modifier = Modifier
            .width(300.dp)
            .padding(8.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth(1f)
                .padding(8.dp)) {
                Text(
                    text = (networkResult.cardDTO.scheme ?: "Unknown brand").toUpperCase(Locale.ROOT),
                    textAlign = TextAlign.Center, style = MaterialTheme.typography.subtitle1,
                    fontWeight = Bold,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(8.dp)
                )
                Text(
                    text = networkResult.cardDTO.brand ?: "Unknown type",
                    textAlign = TextAlign.Center,  modifier = Modifier
                            .fillMaxWidth(1f)
                )
                Divider(thickness = 1.5.dp)
                Text(text = "Bank: ${networkResult.cardDTO.bank?.bankName}",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start =8.dp, end = 8.dp, top = 8.dp))
                Text(text = "Website: ${networkResult.cardDTO.bank?.bankWebsite}",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start =8.dp, end = 8.dp, top = 8.dp))
                Text(text = "Phone: ${networkResult.cardDTO.bank?.bankPhone}",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start =8.dp, end = 8.dp, top = 8.dp))
                Divider()
                Text(text = "Country: ${networkResult.cardDTO.country?.name}${networkResult.cardDTO.country?.emoji}",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start =8.dp, end = 8.dp, top = 8.dp))
                Text(text = "Currency: ${networkResult.cardDTO.country?.currency}",   modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start =8.dp, end = 8.dp, top = 8.dp))
            }
        }
    }
}