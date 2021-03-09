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
import com.pekwerike.mintbankicr.viewmodel.NetworkViewModel
import java.io.File
import java.io.FileOutputStream

class CardNumberExtractor(
    private val context: Context,
    private val networkViewModel: NetworkViewModel
) {

    fun getGetCardNumber(imageFilePath: String) {
        val inputImage = InputImage.fromFilePath(context, Uri.fromFile(File(imageFilePath)))
        TextRecognition.getClient().process(inputImage).addOnSuccessListener {
            it?.let {
                for (block in it.textBlocks) {
                    val numbers = checkForCardNumber(block.text)
                    if (kotlin.math.log10(numbers.toDouble()).toInt() + 1 >= 16) {
                        networkViewModel.cardNumberCollected(numbers)
                        deleteCacheFile(imageFilePath)
                        break
                    }
                }
            }
        }
    }

    private fun deleteCacheFile(filePath: String) {
        File(filePath).delete()
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