package com.pekwerike.mintbankicr.integrationtest

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import com.pekwerike.mintbankicr.model.NetworkResult
import com.pekwerike.mintbankicr.repository.implementation.MainRepository
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/*
  Test Overview
    Integration test between the MainRepository and the  Network layer, this test is highly
    dependent on internet connectivity
 */
private const val NETWORK_RESULT_TAG = "NetworkResult"


@HiltAndroidTest
class MainRepositoryNetworkLayerIntegrationTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mainRepository: MainRepository

    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun getCardDetailsTest() {
        runBlocking {
            val networkResult = mainRepository.getCardDetails(
                91
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
                NetworkResult.Loading -> {
                    assertTrue(true)
                }
                NetworkResult.NoInternetConnection -> Log.i(NETWORK_RESULT_TAG, "Baba check your internet")
                NetworkResult.HttpError.HttpError400 -> Log.i(NETWORK_RESULT_TAG, "Bad request omo")
                NetworkResult.HttpError.HttpError404 -> Log.i(NETWORK_RESULT_TAG, "Lmaoo, error 404")
                NetworkResult.NoRequest -> {

                }
            }
        }
    }
}