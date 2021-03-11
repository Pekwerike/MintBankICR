package com.pekwerike.mintbankicr.ocr

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.pekwerike.mintbankicr.utils.deleteCacheFile
import com.pekwerike.mintbankicr.utils.stripStringSpacesAndConvertStringToLong
import java.io.File

class CardCharacterExtractor(
    private val context: Context,
    private val cardNumberCollected: (Long) -> Unit
) {

    fun getGetCardNumber(imageFilePath: String) {
        var cardNumber: Long = -1
        val inputImage = InputImage.fromFilePath(context, Uri.fromFile(File(imageFilePath)))
        TextRecognition.getClient().process(inputImage).addOnSuccessListener {
            it?.let {
                for (block in it.textBlocks) {
                    val numbers = block.text.stripStringSpacesAndConvertStringToLong()
                    if (kotlin.math.log10(numbers.toDouble()).toInt() + 1 >= 13) {
                        cardNumber = numbers
                        deleteCacheFile(imageFilePath)
                        break
                    }
                }
            }
            cardNumberCollected(cardNumber)
        }
    }
}