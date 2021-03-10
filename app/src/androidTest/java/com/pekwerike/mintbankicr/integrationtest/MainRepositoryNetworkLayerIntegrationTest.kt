package com.pekwerike.mintbankicr.integrationtest

import android.util.Log
import com.pekwerike.mintbankicr.model.NetworkResult
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
class MainRepositoryNetworkLayerIntegrationTest {
    private lateinit var mainRepository: MainRepository

    @Before
    fun before() {
        mainRepository = MainRepository()
    }

    @Test
    fun getCardDetailsTest() = runBlocking {
        val networkResult = mainRepository.getCardDetails(
            5559405046992892
        )
        when (networkResult) {
            is NetworkResult.Success -> {
                val cardDTO = networkResult.cardDTO
                Log.i(
                    "NetworkResult",
                    "${cardDTO.country?.name} ${cardDTO.bank?.bankName} ${cardDTO.bank?.bankWebsite}"
                )
                assertEquals("debit", cardDTO.type)
            }
            is NetworkResult.HttpError.UnknownError -> {
                Log.i("NetworkResult", networkResult.errorMessage)
            }
            NetworkResult.Loading -> Log.i("NetworkResult", "Loading")
        }

    }
}