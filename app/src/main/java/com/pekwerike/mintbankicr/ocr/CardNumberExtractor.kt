package com.pekwerike.mintbankicr.ocr

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.pekwerike.mintbankicr.R
import java.lang.NumberFormatException

class CardNumberExtractor(private val context: Context) {

    suspend fun getGetCardNumber(imageFileUri: Uri): Long {
        val inputImage = InputImage.fromFilePath(context, imageFileUri)
        TextRecognition.getClient().process(inputImage).addOnSuccessListener {
            Log.i("Text Recognition", "Image processing successful")

        }.addOnFailureListener {
            Log.i("Text Recognition", "Image processing failed")
        }
        val inputImage2 = InputImage.fromBitmap(
            BitmapFactory
                .decodeResource(context.resources, R.drawable.thomascullen_visa_card), 0
        )
        var cardNumber: Long = -1
        TextRecognition.getClient().process(inputImage2).addOnSuccessListener {
            it?.let {
                it.textBlocks.forEach {
                    val numbers = checkForCardNumber(it.text)
                    if (kotlin.math.log10(numbers.toDouble()).toInt() + 1 >= 16) {
                        cardNumber = numbers
                        Log.i("Text", "Card Number is : $cardNumber")
                    }
                }
            }
        }
        Log.i("Text", cardNumber.toString())
        return cardNumber
    }


    private fun checkForCardNumber(text: String): Long {
        val spaceFreeBlock: String = text.replace("\\s".toRegex(), "")
        try {
            return spaceFreeBlock.toLong()
        } catch (exception: NumberFormatException) {

        }
        return -1
    }
}