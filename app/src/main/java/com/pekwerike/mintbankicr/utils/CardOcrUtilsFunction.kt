package com.pekwerike.mintbankicr.utils

object CardOcrUtilsFunction {
    fun convertStringToLong(text: String): Long {
        val spaceFreeBlock: String = text.replace("\\s".toRegex(), "")
        try {
            return spaceFreeBlock.toLong()
        } catch (exception: NumberFormatException) {

        }
        return -1
    }
}