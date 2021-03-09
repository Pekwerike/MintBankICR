package com.pekwerike.mintbankicr.ocr

import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.pekwerike.mintbankicr.viewmodel.NetworkViewModel

class CardNumberExtractor(private val context: Context,
private val networkViewModel: NetworkViewModel) {

     fun getGetCardNumber(imageFileUri: Uri) {
        val inputImage = InputImage.fromFilePath(context, imageFileUri)

         TextRecognition.getClient().process(inputImage).addOnSuccessListener {
            it?.let {
                for(block in it.textBlocks){
                    val numbers = checkForCardNumber(it.text)
                    if (kotlin.math.log10(numbers.toDouble()).toInt() + 1 >= 16) {
                        networkViewModel.cardNumberCollected(numbers)
                        break
                    }
                }
            }
        }
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