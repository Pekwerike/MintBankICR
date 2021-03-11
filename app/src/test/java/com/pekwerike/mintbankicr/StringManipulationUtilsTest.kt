package com.pekwerike.mintbankicr

import com.pekwerike.mintbankicr.utils.stripStringSpacesAndConvertStringToLong
import junit.framework.Assert.assertEquals
import org.junit.Test

class StringManipulationUtilsTest {

    @Test
    fun test_convertStringToLong_returnsLongWhenGivenAValidStringNumber(){
        assertEquals(78585, "7   8 5 85".stripStringSpacesAndConvertStringToLong())
        assertEquals(385, "385".stripStringSpacesAndConvertStringToLong())
        assertEquals(57643585834538356, "5764 358 5834 538 356".stripStringSpacesAndConvertStringToLong() )
    }

    @Test
    fun test_convertStringToLong_returnsNegativeOneWhenGivenAnInvalidStringNumber(){
        assertEquals(-1, "82ng".stripStringSpacesAndConvertStringToLong() )
    }
}