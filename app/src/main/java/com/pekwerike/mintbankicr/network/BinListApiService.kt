package com.pekwerike.mintbankicr.network

import com.pekwerike.mintbankicr.model.CardDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BinListApiService {
    @Headers("Accept-Version: 3")
    @GET("{card_number}")
    suspend fun getCardDetails(@Path("card_number") cardNumber: Long) : Response<CardDTO>
}