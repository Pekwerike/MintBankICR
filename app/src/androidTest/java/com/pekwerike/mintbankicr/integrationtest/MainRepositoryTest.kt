package com.pekwerike.mintbankicr.integrationtest

import com.pekwerike.mintbankicr.repository.implementation.MainRepository
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainRepositoryTest {
    private lateinit var mainRepository: MainRepository
    @Before
    fun before(){
        mainRepository = MainRepository()
    }

    @Test
    fun getCardDetailsTest(){
        val (number, scheme, type, prepaid, country, bank) = mainRepository.getCardDetails(
            5559405046992892
        )
        assertEquals("debit", type)
    }
}