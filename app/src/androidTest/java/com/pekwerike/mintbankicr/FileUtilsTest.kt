package com.pekwerike.mintbankicr

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.pekwerike.mintbankicr.utils.deleteCacheFile
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.io.File
import java.io.FileOutputStream

class FileUtilsTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun deleteCacheFileTest() {
        // create a random file in the memory
        val randomFile = File(
            context.getExternalFilesDir(null),
            "randomFile${System.currentTimeMillis()}.jpg"

        )
        FileOutputStream(randomFile).apply{
            write(324)
            close()
        }
        // test that the deletCacheFile function deletes the file from memory
        assertTrue(randomFile.exists())
        deleteCacheFile(randomFile.absolutePath)
        assertTrue(!randomFile.exists())
    }
}