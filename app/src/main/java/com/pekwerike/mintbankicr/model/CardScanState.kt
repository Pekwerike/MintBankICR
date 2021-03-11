package com.pekwerike.mintbankicr.model

sealed class CardScanState{
    data class ScanSuccessful(val extractedCardNumber: Long) : CardScanState()
    object ScanningInProgress : CardScanState()
    object ScanUnsuccessful : CardScanState()
    object NoScan : CardScanState()
}