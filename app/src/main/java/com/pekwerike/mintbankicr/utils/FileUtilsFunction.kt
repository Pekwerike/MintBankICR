package com.pekwerike.mintbankicr.utils

import java.io.File


fun deleteCacheFile(filePath: String) {
    val cacheFile = File(filePath)
    if(!cacheFile.exists()) cacheFile.delete()
}