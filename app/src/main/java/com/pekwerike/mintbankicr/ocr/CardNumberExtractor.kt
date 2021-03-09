package com.pekwerike.mintbankicr.ocr

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.pekwerike.mintbankicr.R
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel
import java.lang.NumberFormatException

class CardNumberExtractor(private val context: Context,
private val mainActivityViewModel: MainActivityViewModel) {

     fun getGetCardNumber(imageFileUri: Uri) {
        val inputImage = InputImage.fromFilePath(context, imageFileUri)

         TextRecognition.getClient().process(inputImage).addOnSuccessListener {
            it?.let {
                for(block in it.textBlocks){
                    val numbers = checkForCardNumber(it.text)
                    if (kotlin.math.log10(numbers.toDouble()).toInt() + 1 >= 16) {


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