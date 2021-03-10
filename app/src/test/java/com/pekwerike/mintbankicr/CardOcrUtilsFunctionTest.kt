package com.pekwerike.mintbankicr

import com.pekwerike.mintbankicr.utils.CardOcrUtilsFunction
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class CardOcrUtilsFunctionTest {
    private lateinit var cardOcrUtilsFunction: CardOcrUtilsFunction

    @Before
    fun before() {
        cardOcrUtilsFunction = CardOcrUtilsFunction
    }

    @Test
    fun test_convertStringToLong_returnsLongWhenGivenAValidStringNumber(){
        assertEquals(895490, cardOcrUtilsFunction.convertStringToLong("895 490"))
        assertEquals(77, cardOcrUtilsFunction.convertStringToLong("77"))
        assertEquals(123456789, cardOcrUtilsFunction.convertStringToLong("123 456  78    9"))
    }

    @Test
    fun test_convertStringToLong_returnsNegativeOneWhenGivenAnInvalidStringNumber(){
        assertEquals(-1 ,cardOcrUtilsFunction.convertStringToLong("9gh"))
    }

}