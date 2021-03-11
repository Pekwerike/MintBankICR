package com.pekwerike.mintbankicr.utils

/* Extension function to strip a string of all white spaces and convert the string to long
This function returns the numeric equivalent of the converted string or -1 if the conversion
fails
 */
fun String.stripStringSpacesAndConvertStringToLong() : Long{
    val spaceFreeString = replace("\\s".toRegex(), "")
    try {
        return spaceFreeString.toLong()
    }catch (numberFormatException : NumberFormatException){

    }
    return -1
}

