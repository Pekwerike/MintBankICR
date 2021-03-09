package com.pekwerike.mintbankicr

import com.pekwerike.mintbankicr.ocr.CardNumberExtractor
import org.junit.Before

class CardNumberExtractorTest {
    private lateinit var cardNumberExtractor: CardNumberExtractor
    ApplicationPr

    @Before
    fun before(){
        cardNumberExtractor = CardNumberExtractor()
    }

}