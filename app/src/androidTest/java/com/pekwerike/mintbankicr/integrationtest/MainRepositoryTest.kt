package com.pekwerike.mintbankicr.integrationtest

import android.util.Log
import com.pekwerike.mintbankicr.repository.implementation.MainRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/*
  Test Overview
    Integration test between the MainRepository and the  Network layer, this test is highly
    dependent on internet connectivity
 */
class MainRepositoryTest {
    private lateinit var mainRepository: MainRepository

    @Before
    fun before() {
        mainRepository = MainRepository()
    }

    @Test
    fun getCardDetailsTest() = runBlocking {
        val (number, scheme, brand,  type, prepaid, country, bank) = mainRepository.getCardDetails(
            5559405046992892
        )
        Log.i("NetworkResult", "${country?.name} ${bank?.bankName} ${bank?.bankWebsite}")
        assertEquals("debit", type)
    }
}