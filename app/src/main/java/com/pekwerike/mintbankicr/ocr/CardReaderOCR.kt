package com.pekwerike.mintbankicr.ocr

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class CardReaderOCR(private val activityContext: Context) : ImageAnalysis.Analyzer{
    override fun analyze(image: ImageProxy) {
        // get the byteBuffer of the image and parse it to ML Kit
        val imageByteBuffer = image.planes[0].buffer
        image.close()
    }

}