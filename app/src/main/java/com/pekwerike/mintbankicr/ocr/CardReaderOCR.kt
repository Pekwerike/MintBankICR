package com.pekwerike.mintbankicr.ocr

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.media.Image
import android.renderscript.ScriptGroup
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.pekwerike.mintbankicr.R
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer

class CardReaderOCR(private val activityContext: Context) : ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(image: ImageProxy) {
        // get the byteBuffer of the image and parse it to ML Kit
        val imageByteBuffer = image.planes[0].buffer
        image.image?.let {
            extractCreditCardNumber(
                imageByteBuffer,
                image.imageInfo.rotationDegrees,
                image.height,
                image.width, it
            )
        }
        //   Log.i("Text gotten", imageByteBuffer.toString())
        image.close()
    }

    private fun extractCreditCardNumber(
        imageByteBuffer: ByteBuffer, imageRotationDegrees: Int,
        imageHeight: Int, imageWidth: Int,
        image : Image
    ) {


       val inputImage2 = InputImage.fromBitmap(BitmapFactory
           .decodeResource(activityContext.resources, R.drawable.kio), 0)

        val inputImage3 = InputImage.fromMediaImage(image, imageRotationDegrees)
        val textRecognizer = TextRecognition.getClient()

        textRecognizer.process(inputImage3)
            .addOnSuccessListener {
                Log.i("Text gotten", "Sucessful")
                Log.i("Text gotten", it.text)
                it?.let {
                    it.textBlocks.forEach {
                        it?.let {
                            it.lines.forEach {
                                it?.let {
                                    it.elements.forEach {
                                        it?.let {
                                            Log.i("Text gotten", "Element: ${it.text}")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            .addOnFailureListener {
               Log.i("Text gotten", "Recognition failed")
            }
    }
}