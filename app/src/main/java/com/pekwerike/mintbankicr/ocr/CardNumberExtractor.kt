package com.pekwerike.mintbankicr.ocr

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Path
import android.net.Uri
import android.util.Log
import androidx.compose.ui.graphics.painter.BitmapPainter
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.pekwerike.mintbankicr.utils.CardOcrUtilsFunction
import com.pekwerike.mintbankicr.viewmodel.NetworkViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class CardNumberExtractor(
    private val context: Context,
    private val networkViewModel: NetworkViewModel
) {

    suspend fun getGetCardNumber(imageFilePath: String) {
        var cardNumber: Long = -1
        val inputImage = InputImage.fromFilePath(context, Uri.fromFile(File(imageFilePath)))
        TextRecognition.getClient().process(inputImage).addOnSuccessListener {
            it?.let {
                for (block in it.textBlocks) {
                    val numbers = CardOcrUtilsFunction.convertStringToLong(block.text)
                    if (kotlin.math.log10(numbers.toDouble()).toInt() + 1 >= 16) {
                        cardNumber = numbers
                        deleteCacheFile(imageFilePath)
                        break
                    }
                }
            }
            networkViewModel.cardNumberCollected(cardNumber)
        }
    }

    private fun deleteCacheFile(filePath: String) {
        File(filePath).delete()
    }

}